package ch.usi.ee.io;

import java.io.IOException;
import java.util.Stack;

public class FileWriter extends FileReader {
    /**
     * Writes a Stack of Strings to a file.
     *
     * @param data     The Stack of Strings to write to the file.
     * @param filename The name of the file to write the data to.
     * @throws IOException
     */
    public static void writeData(Stack<String> data, String filename) throws IOException {
        java.io.FileWriter writer = new java.io.FileWriter(filename, false);

        try {
            for (String s : data) {
                writer.write(s + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
