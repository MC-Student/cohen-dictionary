package cohen.wordle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

public class WordleDictionary
{
    private final HashMap<String, String> dictionary;

    public WordleDictionary() throws IOException
    {
        File file = new File("src/main/resources/dictionary.txt");

        this.dictionary = new HashMap<>();

        BufferedReader br = new BufferedReader(new FileReader(file));

        String currentLine;

        while ((currentLine = br.readLine()) != null)
        {
            String[] entry = currentLine.split(" ", 2);

            String definition;

            if (entry.length == 2)
            {
                definition = entry[1];
            }
            else
            {
                definition = "";
            }

            dictionary.put(entry[0], definition);
        }
    }

    public Set<String> getList()
    {
        return dictionary.keySet();
    }

    public String getDefinition(String word)
    {
        return dictionary.get(word.toUpperCase());
    }
}