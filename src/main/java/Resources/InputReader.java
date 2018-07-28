package Resources;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class InputReader {

    File file;
    Scanner inputFile;
    String result;
    StringTokenizer stok;

    public InputReader(String filename) throws IOException
    {
        String result = "";

        File file = new File(filename);
        Scanner inputFile = new Scanner(file);

        while(inputFile.hasNext())
            result = result + "\n" + inputFile.nextLine();

        stok = new StringTokenizer(result, " \n\r\t");

        inputFile.close();
    }

    public String NextToken()
    {
        return stok.nextToken();
    }

    public boolean EndOfFile ()
    {
        return !stok.hasMoreTokens();
    }

    //Fun��o para mostrar na tela todos os Tokens do arquivo, separados por \n.
    public void Show_Tokens ()
    {
        while (!EndOfFile())
        {
            System.out.println(NextToken());
        }
    }

}
