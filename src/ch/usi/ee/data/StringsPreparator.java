package ch.usi.ee.data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Stack;

public class StringsPreparator {
    /**
     * Prepares a file with strings of length n to be used in the experiments.
     *
     * @param stringsRawSource The file to be used as source.
     * @param stringLength     The length of the strings to be generated.
     * @return True if the file was successfully written, false otherwise.
     */
    public static boolean prepareStrings(String stringsRawSource, int stringLength) {
        Stack<String> filteredStrings = null;

        try {
            filteredStrings = DataGenerator.readAndFilterStringsData(stringsRawSource, stringLength);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            return false;
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            DataGenerator.writeData(filteredStrings, "filtered_strings.txt");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
}
