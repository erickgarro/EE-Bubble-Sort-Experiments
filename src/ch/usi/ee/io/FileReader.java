package ch.usi.ee.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

import ch.usi.ee.Main;
import ch.usi.ee.enums.DataType;


public class FileReader {
//    /**
//     * Reads the data from the file and returns it as an array of the specified type.
//     *
//     * @param type      the type of the array to return
//     * @param ordering  the ordering of the array to return
//     * @param arraySize the size of the array to return
//     * @param <T>       the type of the array to return
//     * @return array of the specified type
//     * @throws FileNotFoundException
//     */
//
//    public static <T> T[] readData(DataType type, DataOrdering ordering, int arraySize) throws FileNotFoundException {
//        String filename = "/data/" + type.toString().toLowerCase() + "_" + ordering.toString().toLowerCase() + ".txt";
//        Class<T> classType = getClass(type);
//        T[] array = (T[]) java.lang.reflect.Array.newInstance(classType, arraySize);
//        Scanner scanner;
//
//        try {
//            scanner = new Scanner(Experiments.class.getResourceAsStream(filename));
//        } catch (Exception e) {
//            throw new FileNotFoundException("File not found");
//        }
//
//        for (int i = 0; i < arraySize; i++) {
//            array[i] = classType.cast(scanner.next());
//        }
//
//        return array;
//    }

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

        System.out.println("Words filtered: " + words.size());
        System.out.println("Length of words: " + stringLength);

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
            case INTEGERS:
                return (Class<T>) int[].class;
            case FLOATS:
                return (Class<T>) float[].class;
            case SHORTS:
                return (Class<T>) short[].class;
            case STRINGS:
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


