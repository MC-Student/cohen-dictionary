package cohen.wordle;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class WordleController
{
    private final WordleGame wordleGame;
    private final WordleDictionary dictionary;
    private final JButton enter;
    private final JButton backspace;
    private JLabel[][] letters;
    private JButton[] keyboardRow1;
    private JButton[] keyboardRow2;
    private JButton[] keyboardRow3;
    private int nextEmpty = 0;

    public WordleController(WordleGame wordleGame, WordleDictionary dictionary, JLabel[][] letters,
                            JButton[] keyboardRow1, JButton[] keyboardRow2, JButton[] keyboardRow3,
                            JButton enter, JButton backspace)
    {
        this.wordleGame = wordleGame;
        this.dictionary = dictionary;
        this.letters = letters;
        this.keyboardRow1 = keyboardRow1;
        this.keyboardRow2 = keyboardRow2;
        this.keyboardRow3 = keyboardRow3;
        this.enter = enter;
        this.backspace = backspace;
    }

    /*2d array of jlabels
        arranged in a jpanel with grid layout
        jbuttons - 1 per letter, enter, backspace
        handle "not in word list"
        color code guess, dn need to color keyboard
        6 guesses (5 letter words)
        handle on-screen keyboard + keys
        * */

    public void addLetter(String letter)
    {
        if ((fullWord(nextEmpty) && wordleGame.getGuesses() == nextEmpty / 5)
                || !fullWord(nextEmpty))
        {
            int row = (nextEmpty < 30) ? nextEmpty / 5 : 5;
            letters[row][nextEmpty % 5].setText(letter.toUpperCase());
            nextEmpty++;
        }
    }

    public void enterGuess()
    {
        if (fullWord(nextEmpty) && wordleGame.getGuesses() < nextEmpty / 5)
        {
            int row = (nextEmpty < 30) ? nextEmpty / 5 : 5;
            StringBuilder guess = new StringBuilder();

            for (int i = 0; i < 5; i++)
            {
                guess.append(letters[row][i].getText());
            }

            String word = guess.toString();
            CharStatus[] results = wordleGame.guess(word);

            for (int i = 0; i < 5; i++)
            {
                if (results[i] == CharStatus.NotFound)
                {
                    letters[row][i].setBackground(Color.LIGHT_GRAY);
                }
                else if (results[i] == CharStatus.WrongPlace)
                {
                    letters[row][i].setBackground(Color.ORANGE);
                }
                else
                {
                    letters[row][i].setBackground(Color.green);
                }
            }
        }
    }

    public void backspace()
    {
        if ((fullWord(nextEmpty) && wordleGame.getGuesses() != nextEmpty / 5)
                || !fullWord(nextEmpty) && nextEmpty != 0)
        {
            int row = (nextEmpty < 30) ? nextEmpty / 5 : 5;
            letters[row][(nextEmpty - 1) % 5].setText("");
            nextEmpty--;
        }
    }

    private boolean fullWord(int next)
    {
        return List.of(5, 10, 15, 20, 25, 30).contains(next);
    }
}
