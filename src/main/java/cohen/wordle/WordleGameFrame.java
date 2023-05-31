package cohen.wordle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

public class WordleGameFrame extends JFrame
{
    private final WordleController controller;

    public WordleGameFrame(WordleGame wordleGame)
    {
        JLabel[][] letters = new JLabel[6][5];

        controller = new WordleController(wordleGame, letters);

        JPanel centerPanel = new JPanel(new GridLayout(6, 5));

        for (int i = 0; i < letters.length; i++)
        {
            for (int j = 0; j < letters[0].length; j++)
            {
                JLabel box = new JLabel();
                box.setHorizontalAlignment(JLabel.CENTER);
                box.setBorder(BorderFactory.createLineBorder(Color.black));
                letters[i][j] = box;
                centerPanel.add(letters[i][j]);
            }
        }

        List<Character> keys1 = List.of(new Character[]{'Q', 'W', 'E', 'R', 'T', 'Y',
                'U', 'I', 'O', 'P'});
        List<Character> keys2 = List.of(new Character[]{'A', 'S', 'D', 'F', 'G', 'H',
                'J', 'K', 'L'});
        List<Character> keys3 = List.of(new Character[]{'Z', 'X', 'C', 'V', 'B', 'N', 'M'});

        JPanel kbRow1 = new JPanel(new GridLayout(1, 10));

        JButton[] keyboardRow1 = new JButton[10];

        for (int i = 0; i < keyboardRow1.length; i++)
        {
            final JButton key = new JButton(String.valueOf(keys1.get(i)));
            key.setHorizontalAlignment(JButton.CENTER);
            key.addActionListener(e -> pressedKey(key));
            keyboardRow1[i] = key;
            kbRow1.add(keyboardRow1[i]);
        }

        JPanel kbRow2 = new JPanel(new GridLayout(1, 9));

        JButton[] keyboardRow2 = new JButton[9];

        for (int i = 0; i < keyboardRow2.length; i++)
        {
            final JButton key = new JButton(String.valueOf(keys2.get(i)));
            key.setHorizontalAlignment(JButton.CENTER);
            key.addActionListener(e -> pressedKey(key));
            keyboardRow2[i] = key;
            kbRow2.add(keyboardRow2[i]);
        }

        JPanel kbRow3 = new JPanel(new GridLayout(1, 10));

        JButton[] keyboardRow3 = new JButton[10];

        JButton backspace = new JButton("Back");
        backspace.setHorizontalAlignment(JButton.CENTER);
        backspace.addActionListener(e -> back());
        keyboardRow3[0] = backspace;
        kbRow3.add(keyboardRow3[0]);

        for (int i = 1; i < keyboardRow3.length - 3; i++)
        {
            final JButton key = new JButton(String.valueOf(keys3.get(i - 1)));
            key.setHorizontalAlignment(JButton.CENTER);
            key.addActionListener(e -> pressedKey(key));
            keyboardRow3[i] = key;
            kbRow3.add(keyboardRow3[i]);
        }

        JButton enter = new JButton("Enter");
        enter.setHorizontalAlignment(JButton.CENTER);
        enter.addActionListener(e -> enter());
        keyboardRow3[keyboardRow3.length - 1] = enter;
        kbRow3.add(keyboardRow3[keyboardRow3.length - 1]);

        JPanel keyboard = new JPanel(new GridLayout(3, 1));

        keyboard.add(kbRow1);
        keyboard.add(kbRow2);
        keyboard.add(kbRow3);

        JPanel mainPanel = new JPanel(new BorderLayout());

        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(keyboard, BorderLayout.SOUTH);

        setContentPane(mainPanel);
        setSize(600, 600);
        setTitle("Wordle Game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setFocusable(true);
        requestFocus();

        addKeyListener(new KeyListener()
        {
            @Override
            public void keyTyped(KeyEvent e)
            {
                char character = e.getKeyChar();

                if (Character.isAlphabetic(character))
                {
                    controller.addLetter(String.valueOf(e.getKeyChar()));
                }
                else if (character == KeyEvent.VK_BACK_SPACE)
                {
                    controller.backspace();
                }
                else if (character == KeyEvent.VK_ENTER)
                {
                    controller.enterGuess();
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
    }

    public void pressedKey(JButton key)
    {
        controller.addLetter(key.getText());
        requestFocus();
    }

    public void enter()
    {
        controller.enterGuess();
        requestFocus();
    }

    public void back()
    {
        controller.backspace();
        requestFocus();
    }

    public String checkStatusForMessage()
    {
        return controller.getClosingMessage();
    }

}