package cohen.wordle;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WordleDictionaryTest
{

    @Test
    public void getList() throws IOException
    {
        WordleDictionary wordle = new WordleDictionary();

        Set<String> words = wordle.getList();

        assertEquals(words.size(), 167964);
    }

    @Test
    public void getDefinition() throws IOException
    {
        WordleDictionary wordle = new WordleDictionary();

        String result = wordle.getDefinition("formatting");

        assertEquals(result, "<format=v> [v]");
    }

}