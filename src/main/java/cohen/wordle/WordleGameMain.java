package cohen.wordle;

import java.io.IOException;

public class WordleGameMain
{
    public static void main(String[] args) throws IOException
    {
        WordleDictionary dictionary = new WordleDictionary();
        WordleGame wordleGame = new WordleGame(dictionary);
        WordleGameFrame frame = new WordleGameFrame(wordleGame, dictionary);
        frame.setVisible(true);
    }
}