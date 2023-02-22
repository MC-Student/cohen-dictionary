package cohen.wordle;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class WordleGame
{
    private final WordleDictionary dictionary = new WordleDictionary();
    private final Random random = new Random();
    String actualWord;

    public WordleGame() throws IOException
    {
        do
        {
            int picked = random.nextInt(167963);
            this.actualWord = dictionary.words.get(picked);
        }
        while(actualWord.length() != 5);
    }

    public String getActualWord ()
    {
        return actualWord;
    }

    public CharStatus[] guess (String guess)
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

        if (dictionary.words.contains(guess) && guess.length() == 5)
        {
            for (int i = 0; i < guess.length(); i++)
            {
                if(actualWord.contains(String.valueOf(guess.charAt(i))))
                {
                    if(guess.charAt(i) == actualWord.charAt(i))
                    {
                        results[i] = CharStatus.Correct;
                    }
                    else
                    {
                        results[i] = CharStatus.WrongPlace;
                    }
                }
                /*else
                {
                    results[i] = CharStatus.NotFound;
                }*/
            }
        }

        else
        {
            System.out.println("Guess Invalid");
            return null;
        }

        System.out.println(Arrays.toString(results));
        return results;
    }
}
