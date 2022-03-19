package ch.usi.ee.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Stack;

import ch.usi.ee.enums.DataType;


public class FileReader {
    /**
    /**
     * Read a file with Strings and return a Stack of Strings.
     *
     * @param filename the name of the file to read
     * @return a Stack of Strings
     * @throws FileNotFoundException
     */
    public static Stack<String> readAndFilterStringsData(String filename, int stringLength) throws IOException {
        Stack<String> words = new Stack<>();
        BufferedReader reader;

        if (stringLength > 1) {
            try {
                reader = new BufferedReader(new java.io.FileReader(filename));

            } catch (Exception e) {
                throw new FileNotFoundException("File not found");
            }

            while (reader.ready()) {
                String line = reader.readLine();
                String[] wordsInLine = line.split(" ");

                for (String word : wordsInLine) {
                    if (word.length() == stringLength) {
                        words.push(word);
                    }
                }

            }
            reader.close();

            System.out.println("Processing string files...");
            System.out.println("  Filtering strings with length: " + stringLength);


            if (words.size() == 0) {
                System.out.println("No strings with length " + stringLength + " were found.\n");
                System.out.println("Try invoking the program again with a different length.\n");
            }
        }
        return words;
    }

    /**
     * Returns the class of the specified type.
     *
     * @param dataType the type of the array to return
     * @param <T>      the type of the array to return
     * @return the class of the specified type
     */
    private static <T> Class<T> getClass(DataType dataType) {
        switch (dataType) {
            case INTEGER:
                return (Class<T>) int[].class;
            case FLOAT:
                return (Class<T>) float[].class;
            case SHORT:
                return (Class<T>) short[].class;
            case STRING:
                return (Class<T>) String[].class;
            default:
                throw new IllegalArgumentException("Unknown data type");
        }
    }

    /**
     * Reads the data from the file and returns it as an array of strings
     *
     * @param filename the name of the file to read
     * @return array of strings
     * @throws FileNotFoundException
     */
    public static String[] readData(String filename) throws IOException {
        Stack<String> words = new Stack<>();
        BufferedReader reader;

        try {
            reader = new BufferedReader(new java.io.FileReader(filename));

        } catch (Exception e) {
            throw new FileNotFoundException("File not found");
        }

        while (reader.ready()) {
            String line = reader.readLine();
            String[] wordsInLine = line.split(" ");

            for (String word : wordsInLine) {
                words.push(word);
            }

        }
        reader.close();

        return words.toArray(new String[words.size()]);
    }
}


