package cohen.wordle;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class WordleGameMain
{
    public static void main(String[] args) throws IOException
    {
        WordleDictionary dictionary = new WordleDictionary();
        WordleGame wordleGame = new WordleGame(dictionary);
        WordleGameFrame frame = new WordleGameFrame(wordleGame);
        frame.addKeyListener(new KeyListener()
        {
            @Override
            public void keyTyped(KeyEvent e)
            {
                String message = frame.checkStatusForMessage();
                if (!message.equals("n/a"))
                {
                    JOptionPane.showMessageDialog(new JFrame(), "Game over! " + message);

                    frame.setVisible(false);
                    frame.dispose();
                    System.exit(0);
                }
            }

            @Override
            public void keyPressed(KeyEvent e)
            {

            }

            @Override
            public void keyReleased(KeyEvent e)
            {

            }
        });
        frame.setVisible(true);
    }
}