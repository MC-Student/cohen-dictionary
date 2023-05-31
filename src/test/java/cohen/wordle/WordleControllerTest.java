package cohen.wordle;

import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static cohen.wordle.CharStatus.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class WordleControllerTest
{
    WordleGame wordleGame = mock();
    JLabel[][] letters = new JLabel[][]
            {
                    {mock(), mock(), mock(), mock(), mock()},
                    {mock(), mock(), mock(), mock(), mock()},
                    {mock(), mock(), mock(), mock(), mock()},
                    {mock(), mock(), mock(), mock(), mock()},
                    {mock(), mock(), mock(), mock(), mock()},
                    {mock(), mock(), mock(), mock(), mock()}
            };

    @Test
    public void addLetter()
    {
        //given
        WordleController controller = new WordleController(wordleGame, letters);

        //when
        controller.addLetter("t");
        controller.addLetter("a");
        controller.addLetter("u");

        //then
        verify(letters[0][0]).setText("T");
        verify(letters[0][1]).setText("A");
        verify(letters[0][2]).setText("U");
    }

    @Test
    public void backspaceAfterNone()
    {
        //given
        WordleController controller = new WordleController(wordleGame, letters);

        //when
        controller.backspace();

        //then
        verifyNoInteractions(wordleGame);
    }

    @Test
    public void backspaceAfterOne()
    {
        //given
        WordleController controller = new WordleController(wordleGame, letters);

        //when
        controller.addLetter("t");
        controller.backspace();
        controller.addLetter("s");


        //then
        verify(letters[0][0]).setText("T");
        verify(letters[0][0]).setText("");
        verify(letters[0][0]).setText("S");

        assertEquals(1, controller.currentGuess.length());
    }

    @Test
    public void backspaceAfterMultiple()
    {
        //given
        WordleController controller = new WordleController(wordleGame, letters);

        //when
        controller.addLetter("t");
        controller.addLetter("a");
        controller.backspace();
        controller.addLetter("u");

        //then
        verify(letters[0][0]).setText("T");
        verify(letters[0][1]).setText("A");
        verify(letters[0][1]).setText("");
        verify(letters[0][1]).setText("U");

        assertEquals(2, controller.currentGuess.length());
    }

    @Test
    public void enterGuess()
    {
        //given
        WordleController controller = new WordleController(wordleGame, letters);
        CharStatus[] results = {NotFound, NotFound, NotFound, WrongPlace, Correct};
        ArrayList<String> dictionary = new ArrayList<>();
        dictionary.add("TAUPE");
        dictionary.add("PHONE");
        String guess = "TAUPE";
        doReturn(dictionary).when(wordleGame).getWordleWords();
        doReturn(results).when(wordleGame).guess(guess);

        //when
        controller.addLetter("t");
        controller.addLetter("a");
        controller.addLetter("u");
        controller.addLetter("p");
        controller.addLetter("e");

        controller.enterGuess();

        //then
        verify(letters[0][0]).setBackground(Color.LIGHT_GRAY);
        verify(letters[0][0]).setOpaque(true);
        verify(letters[0][1]).setBackground(Color.LIGHT_GRAY);
        verify(letters[0][1]).setOpaque(true);
        verify(letters[0][2]).setBackground(Color.LIGHT_GRAY);
        verify(letters[0][2]).setOpaque(true);
        verify(letters[0][3]).setBackground(Color.ORANGE);
        verify(letters[0][3]).setOpaque(true);
        verify(letters[0][4]).setBackground(Color.green);
        verify(letters[0][4]).setOpaque(true);

        assertEquals(0, controller.currentGuess.length());
    }
}