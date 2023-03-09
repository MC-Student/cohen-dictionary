package cohen.wordle;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static cohen.wordle.CharStatus.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.mockito.Mockito.doReturn;

class WordleGameTest
{
    @Test
    void guessCorrect()
    {
        //given
        WordleDictionary dictionary = Mockito.mock(WordleDictionary.class);
        Set<String> words = new HashSet<>(List.of("APPLE"));
        doReturn(words).when(dictionary).getList();
        WordleGame wordle = new WordleGame(dictionary);

        //when
        CharStatus[] result = wordle.guess("APPLE");


        //then
        CharStatus[] correct = {Correct, Correct, Correct, Correct, Correct};
        assertArrayEquals(correct, result);
    }

    @Test
    void guessIncorrect()
    {
        //given
        WordleDictionary dictionary = Mockito.mock(WordleDictionary.class);
        Set<String> words = new HashSet<>(List.of("APPLE"));
        doReturn(words).when(dictionary).getList();
        WordleGame wordle = new WordleGame(dictionary);

        //when
        CharStatus[] result = wordle.guess("SHOWS");


        //then
        CharStatus[] correct = {NotFound, NotFound, NotFound, NotFound, NotFound};
        assertArrayEquals(correct, result);
    }

    @Test
    void guessPartial()
    {
        //given
        WordleDictionary dictionary = Mockito.mock(WordleDictionary.class);
        Set<String> words = new HashSet<>(List.of("APPLE"));
        doReturn(words).when(dictionary).getList();
        WordleGame wordle = new WordleGame(dictionary);

        //when
        CharStatus[] result = wordle.guess("shone");


        //then
        CharStatus[] correct = {NotFound, NotFound, NotFound, NotFound, Correct};
        assertArrayEquals(correct, result);
    }

    @Test
    void guessWrongPlace()
    {
        //given
        WordleDictionary dictionary = Mockito.mock(WordleDictionary.class);
        Set<String> words = new HashSet<>(List.of("APPLE"));
        doReturn(words).when(dictionary).getList();
        WordleGame wordle = new WordleGame(dictionary);

        //when
        CharStatus[] result = wordle.guess("tiles");


        //then
        CharStatus[] correct = {NotFound, NotFound, WrongPlace, WrongPlace, NotFound};
        assertArrayEquals(correct, result);
    }
}