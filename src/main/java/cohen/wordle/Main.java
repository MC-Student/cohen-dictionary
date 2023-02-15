package cohen.wordle;

import java.io.IOException;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        WordleDictionary test = new WordleDictionary();
        System.out.println(test.getList().get(0));
    }
}
