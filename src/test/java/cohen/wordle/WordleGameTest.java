package cohen.wordle;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class WordleGameTest
{
    @Test
    void guess() throws IOException
    {
        //given
        WordleGame wordle = new WordleGame();

        String word = wordle.getActualWord();

        //when
        CharStatus[] correct = {CharStatus.Correct, CharStatus.Correct, CharStatus.Correct, CharStatus.Correct, CharStatus.Correct};

        CharStatus[] result = wordle.guess(word);

        //then
        assertEquals(word.length(), 5);
        assertArrayEquals(correct, result);
    }

}