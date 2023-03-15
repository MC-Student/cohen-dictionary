package cohen.wordle;

import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

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

    /*@Test
    public void enterGuess()
    {
        //given
        WordleController controller = new WordleController(wordleGame, letters);
        StringBuilder mGuess = mock();
        StringBuilder guess = new StringBuilder("TAUPE");
        doReturn(guess).when(controller).getGuess(mGuess);

        //when
        letters

        if (!currentGuess.toString().equals("") && currentGuess.length() == 5)
        {

            String word = currentGuess.toString().toUpperCase();

            if (wordleGame.getWordleWords().contains(word))
            {
                CharStatus[] results = wordleGame.guess(word);

                for (int i = 0; i < results.length; i++)
                {
                    if (results[i] == CharStatus.NotFound)
                    {
                        letters[rowCount][i].setBackground(Color.LIGHT_GRAY);
                        letters[rowCount][i].setOpaque(true);
                    }
                    else if (results[i] == CharStatus.WrongPlace)
                    {
                        letters[rowCount][i].setBackground(Color.ORANGE);
                        letters[rowCount][i].setOpaque(true);
                    }
                    else
                    {
                        letters[rowCount][i].setBackground(Color.green);
                        letters[rowCount][i].setOpaque(true);
                    }
                }

                rowCount++;
                charsTyped = 0;
                currentGuess = new StringBuilder(5);
            }

            else
            {
                JOptionPane.showMessageDialog(new JFrame(),
                        "Word not in dictionary, please try a different word");
            }
        }
    }*/
}