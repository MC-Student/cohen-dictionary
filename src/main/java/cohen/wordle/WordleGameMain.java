package cohen.wordle;

import java.io.IOException;

public class WordleGameMain
{
    public static void main(String[] args) throws IOException
    {
        WordleDictionary dictionary = new WordleDictionary();
        WordleGame wordleGame = new WordleGame(dictionary);
        WordleGameFrame frame = new WordleGameFrame(wordleGame);
        frame.setVisible(true);
        /*if (!frame.isVisible())
        {
            JOptionPane.showMessageDialog(frame, "Game over! " + frame.getMessage());
            frame.setVisible(false);
            frame.dispose();
        }*/
    }
}