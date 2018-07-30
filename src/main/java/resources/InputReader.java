package resources;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class InputReader {

  StringTokenizer stok;

  /**
   * Reads a file in the constructor and creates a nextToken() method,
   * that returns the next token of the file.
   * @param filename name of the file to be read
   * @throws IOException when IO error occurs
   */
  public InputReader(String filename) throws IOException {
    String result = "";

    File file = new File(filename);
    Scanner inputFile = new Scanner(file);

    while (inputFile.hasNext()) {
      result = result + "\n" + inputFile.nextLine();
    }

    stok = new StringTokenizer(result, " \n\r\t");

    inputFile.close();
  }

  public String nextToken() {
    return stok.nextToken();
  }

  public boolean endOfFile() {
    return !stok.hasMoreTokens();
  }
}
