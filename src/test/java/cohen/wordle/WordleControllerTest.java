package cohen.wordle;

import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static cohen.wordle.CharStatus.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
    }

    @Test
    public void enterValidGuess()
    {
        //given
        WordleGame game = mock();
        WordleController controller = new WordleController(game, letters);
        CharStatus[] results = {NotFound, NotFound, NotFound, WrongPlace, Correct};
        String guess = "TAUPE";
        ArrayList<String> dictionary = new ArrayList<>();
        dictionary.add("TAUPE");

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
    }

    @Test
    public void enterInvalidGuess()
    {
        //given
        WordleController controller = new WordleController(wordleGame, letters);
        WordleController mController = mock();
        String guess = "LARGY";
        ArrayList<String> dictionary = new ArrayList<>();
        dictionary.add("TAUPE");
        doReturn(dictionary).when(wordleGame).getWordleWords();
        doReturn("LARGY").when(mController).getGuess(new StringBuilder(guess));

        //when
        mController.addLetter("l");
        mController.addLetter("a");
        mController.addLetter("r");
        mController.addLetter("g");
        mController.addLetter("y");

        //then
        verify(letters[0][0]).setText("L");
        verify(letters[0][1]).setText("A");
        verify(letters[0][2]).setText("R");
        verify(letters[0][3]).setText("G");
        verify(letters[0][4]).setText("Y");

        verifyNoMoreInteractions(wordleGame);
        assertFalse(wordleGame.gameIsWon());
    }
}