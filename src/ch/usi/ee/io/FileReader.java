package ch.usi.ee.io;

import java.io.FileNotFoundException;
import java.util.Scanner;

import ch.usi.ee.Experiments;
import ch.usi.ee.enums.DataOrdering;
import ch.usi.ee.enums.DataType;


public class FileReader {
    /**
     * Reads the data from the file and returns it as an array of the specified type.
     *
     * @param type      the type of the array to return
     * @param ordering  the ordering of the array to return
     * @param arraySize the size of the array to return
     * @param <T>       the type of the array to return
     * @return array of the specified type
     * @throws FileNotFoundException
     */

    public static <T> T[] readData(DataType type, DataOrdering ordering, int arraySize) throws FileNotFoundException {
        String filename = "/data/" + type.toString().toLowerCase() + "_" + ordering.toString().toLowerCase() + ".txt";
        Class<T> classType = getClass(type);
        T[] array = (T[]) java.lang.reflect.Array.newInstance(classType, arraySize);
        Scanner scanner;

        try {
            scanner = new Scanner(Experiments.class.getResourceAsStream(filename));
        } catch (Exception e) {
            throw new FileNotFoundException("File not found");
        }

        for (int i = 0; i < arraySize; i++) {
            array[i] = classType.cast(scanner.next());
        }

        return array;
    }

    /**
     * Returns the class of the specified type.
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
}


