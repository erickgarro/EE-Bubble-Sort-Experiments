/**
 * Experimentation & Evaluation SP2022
 * USI - Università della Svizzera italiana
 * Project: Java Bubble Sort Experiments
 *
 * Authors: Erick Garro Elizondo & Cindy Guerrero Toro
 */

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
        String[] fullpath = filename.split("/");
        String folder = fullpath[fullpath.length - 2];

        // Check if folder exists in workingDir
        String workingDir = System.getProperty("user.dir");
        if (!workingDir.contains(folder)) {
            new java.io.File(workingDir + "/" + folder).mkdir();
        }
        
        try {
            for (String s : data) {
                writer.write(s + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    /**
     * Gets the current date and time in format yyyy-MM-dd_HH-mm-ss.
     *
     * @return The current date and time as a String.
     */
    public static String getDateTime() {
        java.util.Date date = new java.util.Date();
        return new java.text.SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(date);
    }
}
