package Resources;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class OutputWriter {

    FileWriter fw = null;
    BufferedWriter bw = null;

    public OutputWriter(String outputFile) throws IOException {
        fw = new FileWriter(outputFile);
        bw = new BufferedWriter(fw);
    }

    public void writeString(String str) throws IOException {
       bw.write(str);
    }

    public void close() throws IOException {
        bw.close();
        fw.close();
    }
}
