package cohen.wordle;

import java.io.*;
import java.util.*;

public class WordleDictionary
{
    public final ArrayList<String> words;
    public final ArrayList<String> definitions;

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
                definitions.add(entry[1]); //TODO: Fix this bug!
            }
            else
            {
                definitions.add(null);
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
        return definitions.get(words.indexOf(word.toUpperCase()));
    }
}
