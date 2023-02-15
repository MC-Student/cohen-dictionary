package cohen.wordle;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WordleDictionaryTest
{

    @Test
    public void getList() throws IOException
    {
        WordleDictionary wordle = new WordleDictionary();

        List<String> words = wordle.getList();

        assertEquals(words.size(), 167964);
    }

    @Test
    void getDefinitions() throws IOException
    {
        WordleDictionary wordle = new WordleDictionary();

        List<String> definitions = wordle.getDefinitions();

        assertEquals(definitions.size(), 167964);
    }

    @Test
    public void getDefinition() throws IOException
    {
        WordleDictionary wordle = new WordleDictionary();

        String result = wordle.getDefinition("formatting");

        assertEquals(result, "<format=v> [v]");
    }

}