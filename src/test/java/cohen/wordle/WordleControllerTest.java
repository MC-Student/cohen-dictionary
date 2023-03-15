package cohen.wordle;

import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static cohen.wordle.CharStatus.*;
import static org.junit.jupiter.api.Assertions.*;
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

    StringBuilder guess = mock();

    @Test
    public void addLetter()
    {
        //given
        WordleController controller = new WordleController(wordleGame, letters);

        //when
        controller.addLetter("t");
        guess.append("t");
        controller.addLetter("a");
        guess.append("a");
        controller.addLetter("u");
        guess.append("u");

        //then
        verify(letters[0][0]).setText("T");
        verify(guess).append("t");
        verify(letters[0][1]).setText("A");
        verify(guess).append("a");
        verify(letters[0][2]).setText("U");
        verify(guess).append("u");
    }

    @Test
    public void backspaceAfterNone()
    {
        //given
        WordleController controller = new WordleController(wordleGame, letters);

        //when
        controller.backspace();

        //then
        assertNull(letters[0][0].getText());
    }

    @Test
    public void backspaceAfterOne()
    {
        //given
        WordleController controller = new WordleController(wordleGame, letters);

        //when
        controller.addLetter("t");
        guess.append("t");

        controller.backspace();
        guess.deleteCharAt(0);

        controller.addLetter("s");
        guess.append("s");


        //then
        verify(letters[0][0]).setText("T");
        verify(guess).append("t");

        verify(letters[0][0]).setText("");
        verify(guess).deleteCharAt(0);

        verify(letters[0][0]).setText("S");
        verify(guess).append("s");
    }

    @Test
    public void backspaceAfterMultiple()
    {
        //given
        WordleController controller = new WordleController(wordleGame, letters);

        //when
        controller.addLetter("t");
        guess.append("t");
        controller.addLetter("a");
        guess.append("a");

        controller.backspace();
        guess.deleteCharAt(1);

        controller.addLetter("u");
        guess.append("u");


        //then
        verify(letters[0][0]).setText("T");
        verify(guess).append("t");
        verify(letters[0][1]).setText("A");
        verify(guess).append("a");

        verify(letters[0][1]).setText("");
        verify(guess).deleteCharAt(1);

        verify(letters[0][1]).setText("U");
        verify(guess).append("u");
    }

    @Test
    public void enterValidGuess()
    {
        //given
        WordleController controller = new WordleController(wordleGame, letters);
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

        assertEquals(Color.LIGHT_GRAY, letters[0][0].getBackground());
        verify(letters[0][0]).setBackground(Color.LIGHT_GRAY);
        verify(letters[0][0]).setOpaque(true);

        assertFalse(wordleGame.gameIsWon());
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

        mController.enterGuess();

        //then
        verify(letters[0][0]).setText("L");
        verify(letters[0][1]).setText("A");
        verify(letters[0][2]).setText("R");
        verify(letters[0][3]).setText("G");
        verify(letters[0][4]).setText("Y");
        verify(mController).enterGuess();

        verifyNoMoreInteractions(wordleGame);
        assertFalse(wordleGame.gameIsWon());
    }
}