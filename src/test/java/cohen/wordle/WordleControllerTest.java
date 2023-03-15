package cohen.wordle;

import org.junit.jupiter.api.Test;

import javax.swing.*;

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

    StringBuilder guess = mock();

    @Test
    public void addLetter()
    {
        //given
        WordleController controller = new WordleController(wordleGame, letters);
        doReturn("TAU").when(guess).toString();

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

        assertEquals("TAU", guess.toString());
    }

    @Test
    public void backspace()
    {

    }
}