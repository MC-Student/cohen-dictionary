package cohen.wordle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WordleDictionary
{
    private final ArrayList<String> words;
    private final ArrayList<String> definitions;

    public WordleDictionary() throws IOException
    {
        File file = new File("src/main/resources/dictionary.txt");

        this.words = new ArrayList<>();

        this.definitions = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader(file));

        String currentLine;

        while ((currentLine = br.readLine()) != null)
        {
            String[] entry = currentLine.split(" ", 2);
            words.add(entry[0]);
            if (entry.length == 2)
            {
                definitions.add(entry[1]);
            }
            else
            {
                definitions.add("");
            }

        }
    }

    public List<String> getList()
    {
        return words;
    }

    public List<String> getDefinitions()
    {
        return definitions;
    }

    public String getDefinition(String word)
    {
        int wordLocation = words.indexOf(word.toUpperCase());
        if (wordLocation == -1)
        {
            return null;
        }
        return definitions.get(wordLocation);
    }
}
