package cohen.wordle;

import javax.swing.*;
import java.io.IOException;

public class WordleGameMain
{
    public static void main(String[] args) throws IOException
    {
        WordleDictionary dictionary = new WordleDictionary();
        WordleGame wordleGame = new WordleGame(dictionary);
        WordleGameFrame frame = new WordleGameFrame(wordleGame, dictionary);
        frame.setVisible(true);
        while (frame.isVisible())
        {
            if (wordleGame.getGuesses() == 6)
            {
                String message = wordleGame.gameIsWon()
                        ? "Congratulations, you won!"
                        : "Maybe you will win next time...";
                JOptionPane.showMessageDialog(frame, "Game over! " + message);
                frame.setVisible(false);
                frame.dispose();
            }
        }
    }
}