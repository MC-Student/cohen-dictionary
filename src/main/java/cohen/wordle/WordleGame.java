package cohen.wordle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class WordleGame
{
    private final WordleDictionary dictionary = new WordleDictionary();
    private final Random random = new Random();
    private String actualWord;
    private ArrayList<String> chooseFrom;

    public WordleGame() throws IOException
    {
        chooseFrom = new ArrayList<>();

        for (String word : dictionary.words)
        {
            if (word.length() == 5)
            {
                chooseFrom.add(word);
            }
        }

        int picked = random.nextInt(chooseFrom.size() - 1);
        this.actualWord = chooseFrom.get(picked);

    }

    public String getActualWord()
    {
        return actualWord;
    }

    public CharStatus[] guess(String guess)
    {
        guess = guess.toUpperCase();
        CharStatus[] results = new CharStatus[]
                {
                        CharStatus.NotFound,
                        CharStatus.NotFound,
                        CharStatus.NotFound,
                        CharStatus.NotFound,
                        CharStatus.NotFound,
                };

        if (guess.length() == 5)
        {
            for (int i = 0; i < guess.length(); i++)
            {
                if (actualWord.contains(String.valueOf(guess.charAt(i))))
                {
                    if (guess.charAt(i) == actualWord.charAt(i))
                    {
                        results[i] = CharStatus.Correct;
                    }
                    else
                    {
                        results[i] = CharStatus.WrongPlace;
                    }
                }
            }
        }

        else
        {
            System.out.println("Guess Invalid");
        }

        System.out.println(Arrays.toString(results));
        return results;
    }
}
