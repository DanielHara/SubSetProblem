package Resources;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class InputReader {

  StringTokenizer stok;

  public InputReader(String filename) throws IOException {
    String result = "";

    File file = new File(filename);
    Scanner inputFile = new Scanner(file);

    while(inputFile.hasNext()) {
      result = result + "\n" + inputFile.nextLine();
    }

    stok = new StringTokenizer(result, " \n\r\t");

    inputFile.close();
  }

  public String nextToken() {
    return stok.nextToken();
  }

  public boolean endOfFile () {
    return !stok.hasMoreTokens();
  }
}
