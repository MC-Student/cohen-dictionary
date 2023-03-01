package cohen.wordle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

import static cohen.wordle.CharStatus.Correct;

public class WordleGameFrame extends JFrame
{
    private final WordleGame round = new WordleGame(new WordleDictionary());
    private final CharStatus[] correct = {Correct, Correct, Correct, Correct, Correct};
    int guesses = 0;

    public WordleGameFrame() throws IOException
    {
        /*Guess - JTextField
          Output - JLabel
          Guess Word - Jbutton

          getText, setTest
        * */

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(2, 1));

        JTextField wordGuess = new JTextField("enter your guess here");
        centerPanel.add(wordGuess);
        wordGuess.setHorizontalAlignment(JTextField.CENTER);
        wordGuess.setFont(new Font("Times New Roman", Font.PLAIN, 30));

        JLabel guessResult = new JLabel("results");
        centerPanel.add(guessResult);
        guessResult.setHorizontalAlignment(JLabel.CENTER);
        guessResult.setFont(new Font("Times New Roman", Font.PLAIN, 10));

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        JButton guessButton = new JButton("Guess my word!");
        mainPanel.add(guessButton, BorderLayout.SOUTH);
        guessButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                CharStatus[] result = round.guess(wordGuess.getText());
                guessResult.setText(Arrays.toString(result));
                guesses++;
                if (Objects.equals(Arrays.toString(result), Arrays.toString(correct)))
                {
                    guessButton.setText("After " + guesses + " guess(es), your guess was correct - exit to play a new game!");
                }
                else
                {
                    guessButton.setText(guesses + " guess(es) used, guess again");
                }
            }
        });

        setContentPane(mainPanel);
        setSize(300, 500);
        setTitle("Wordle Game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }
}
