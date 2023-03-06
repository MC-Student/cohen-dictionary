package cohen.wordle;

import javax.swing.*;
import java.awt.*;

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
    private int nextEmpty;

    public WordleController(WordleGame wordleGame, WordleDictionary dictionary, JLabel[][] letters, JButton[] keyboardRow1, JButton[] keyboardRow2, JButton[] keyboardRow3, JButton enter, JButton backspace, int next)
    {
        this.wordleGame = wordleGame;
        this.dictionary = dictionary;
        this.letters = letters;
        this.keyboardRow1 = keyboardRow1;
        this.keyboardRow2 = keyboardRow2;
        this.keyboardRow3 = keyboardRow3;
        this.enter = enter;
        this.backspace = backspace;
        this.nextEmpty = next;
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
        if (nextEmpty % 5 != 0)
        {
            letters[(int) Math.floor(nextEmpty / 5)][(int) (nextEmpty % 5)].setText(letter);
            nextEmpty++;
        }
    }

    public void enterGuess()
    {
        if (nextEmpty % 5 == 0)
        {
            int row = nextEmpty / 5;
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
        if (nextEmpty % 5 != 0)
        {
            letters[(int) Math.floor((nextEmpty - 1) / 5)][(int) ((nextEmpty - 1) % 5)].setText("");
            nextEmpty--;
        }
    }
}
