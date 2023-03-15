package cohen.wordle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import static cohen.wordle.CharStatus.Correct;

public class WordleGame
{
    private final CharStatus[] correct = {Correct, Correct, Correct, Correct, Correct};
    private String actualWord;
    private ArrayList<String> chooseFrom;
    private int guesses;
    private boolean won;

    public WordleGame(WordleDictionary dictionary)
    {
        chooseFrom = new ArrayList<>();
        guesses = 0;

        for (String word : dictionary.getList())
        {
            if (word.length() == 5)
            {
                chooseFrom.add(word);
            }
        }

        Random random = new Random();
        int picked = random.nextInt(chooseFrom.size());
        this.actualWord = chooseFrom.get(picked);
        System.out.println(actualWord);

    }

    public int getGuesses()
    {
        return guesses;
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

        guesses++;
        System.out.println(Arrays.toString(results));
        won = (Arrays.equals(results, correct));
        return results;
    }

    public boolean gameIsWon()
    {
        return won;
    }

    public ArrayList<String> getWordleWords()
    {
        return chooseFrom;
    }
}