package ch.usi.ee.io;

import ch.usi.ee.Experiments;
import ch.usi.ee.enums.DataOrdering;

import java.util.Scanner;

import static ch.usi.ee.enums.DataOrdering.*;


public class FileReaders {

    /**
     * This function reads a file and returns an array of integers
     *
     * @param ordering
     * @return
     */
    protected static int[] readIntegersFile(DataOrdering ordering) {
        int[] array = new int[Experiments.total];
        Scanner scanner;

        if (ordering == SORTED) {
            scanner = new Scanner(Experiments.class.getResourceAsStream("integers_sorted.txt"));
        } else if (ordering == REVERSED) {
            scanner = new Scanner(Experiments.class.getResourceAsStream("integers_reversed.txt"));
        } else if (ordering == RANDOM) {
            scanner = new Scanner(Experiments.class.getResourceAsStream("integers_random.txt"));
        } else {
            throw new IllegalArgumentException("Invalid ordering");
        }

        for (int i = 0; i < Experiments.total; i++) {
            array[i] = scanner.nextInt();
        }
        return array;
    }

    /**
     * This function reads a file and returns an array of shorts
     *
     * @param ordering
     * @return
     */
    protected static short[] readShortsFile(DataOrdering ordering) {
        short[] array = new short[Experiments.total];
        Scanner scanner;

        if (ordering == SORTED) {
            scanner = new Scanner(Experiments.class.getResourceAsStream("shorts_sorted.txt"));
        } else if (ordering == REVERSED) {
            scanner = new Scanner(Experiments.class.getResourceAsStream("shorts_reversed.txt"));
        } else if (ordering == RANDOM) {
            scanner = new Scanner(Experiments.class.getResourceAsStream("shorts_random.txt"));
        } else {
            throw new IllegalArgumentException("Invalid ordering");
        }

        for (int i = 0; i < Experiments.total; i++) {
            array[i] = scanner.nextShort();
        }
        return array;
    }

    /**
     * This function reads a file and returns an array of floats
     *
     * @param ordering
     * @return
     */
    protected static float[] readFloatsFile(DataOrdering ordering) {
        float[] array = new float[Experiments.total];
        Scanner scanner;

        if (ordering == SORTED) {
            scanner = new Scanner(Experiments.class.getResourceAsStream("floats_sorted.txt"));
        } else if (ordering == REVERSED) {
            scanner = new Scanner(Experiments.class.getResourceAsStream("floats_reversed.txt"));
        } else if (ordering == RANDOM) {
            scanner = new Scanner(Experiments.class.getResourceAsStream("floats_random.txt"));
        } else {
            throw new IllegalArgumentException("Invalid ordering");
        }

        for (int i = 0; i < Experiments.total; i++) {
            array[i] = scanner.nextFloat();
        }
        return array;
    }

    /**
     * This function reads a file and returns an array of strings
     *
     * @param ordering
     * @return
     */
    protected static String[] readStringsFile(DataOrdering ordering) {
        String[] array = new String[Experiments.total];
        Scanner scanner;

        if (ordering == SORTED) {
            scanner = new Scanner(Experiments.class.getResourceAsStream("strings_sorted.txt"));
        } else if (ordering == REVERSED) {
            scanner = new Scanner(Experiments.class.getResourceAsStream("strings_reversed.txt"));
        } else if (ordering == RANDOM) {
            scanner = new Scanner(Experiments.class.getResourceAsStream("strings_random.txt"));
        } else {
            throw new IllegalArgumentException("Invalid ordering");
        }

        for (int i = 0; i < Experiments.total; i++) {
            array[i] = scanner.next();
        }
        return array;
    }
}
