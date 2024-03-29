package cohen.wordle;

import javax.swing.*;
import java.awt.*;

public class WordleController
{
    private final WordleGame wordleGame;
    private final JLabel[][] letters;
    StringBuilder currentGuess = new StringBuilder(5);
    private int charsTyped = 0;
    private int rowCount = 0;

    public WordleController(WordleGame wordleGame, JLabel[][] letters)
    {
        this.wordleGame = wordleGame;
        this.letters = letters;
    }

    public void addLetter(String letter)
    {
        if (charsTyped < 5 && !wordleGame.gameIsWon())
        {
            charsTyped++;
            letters[rowCount][charsTyped - 1].setText(letter.toUpperCase());
            currentGuess.append(letter.toUpperCase());
        }
    }

    public void enterGuess()
    {
        if (currentGuess.length() == 5)
        {
            String word = currentGuess.toString().toUpperCase();

            if (wordleGame.getWordleWords().contains(word))
            {
                CharStatus[] results = wordleGame.guess(word);

                for (int i = 0; i < results.length; i++)
                {
                    if (results[i] == CharStatus.NotFound)
                    {
                        letters[rowCount][i].setBackground(Color.LIGHT_GRAY);
                        letters[rowCount][i].setOpaque(true);
                    }
                    else if (results[i] == CharStatus.WrongPlace)
                    {
                        letters[rowCount][i].setBackground(Color.ORANGE);
                        letters[rowCount][i].setOpaque(true);
                    }
                    else
                    {
                        letters[rowCount][i].setBackground(Color.green);
                        letters[rowCount][i].setOpaque(true);
                    }
                }

                rowCount++;
                charsTyped = 0;
                currentGuess = new StringBuilder(5);
            }

            else
            {
                JOptionPane.showMessageDialog(new JFrame(),
                        "Word not in dictionary, please try a different word");
            }
        }
    }

    public void backspace()
    {
        if (wordBeforeGuess() || (currentGuess.length() < 5 && charsTyped > 0))
        {
            letters[rowCount][charsTyped - 1].setText("");
            currentGuess.deleteCharAt(charsTyped - 1);
            charsTyped--;
        }
    }

    private boolean wordBeforeGuess()
    {
        return currentGuess.length() == 5 && rowCount == wordleGame.getGuesses();
    }

    public String getClosingMessage()
    {
        String message;

        if (wordleGame.gameIsWon() && (rowCount < 5))
        {
            message = "Congratulations, you won - that was fast!";
        }
        else if (wordleGame.getGuesses() == 6 && wordleGame.gameIsWon())
        {
            message = "Congratulations, you won just in time!";
        }
        else if (wordleGame.getGuesses() == 6 && !wordleGame.gameIsWon())
        {
            message = "Maybe you will win next time...";
        }
        else
        {
            message = "n/a";
        }
        return message;
    }
}
