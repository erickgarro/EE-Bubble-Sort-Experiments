/**
 * Experimentation & Evaluation SP2022
 * USI - Universit√† della Svizzera italiana
 * Project: Java Bubble Sort Experiments
 *
 * Authors: Erick Garro Elizondo & Cindy Guerrero Toro
 */

package ch.usi.ee.data;

import ch.usi.ee.io.FileWriter;

import java.util.Random;

import static ch.usi.ee.data.Sorters.quickSort;

public class DataGenerator extends FileWriter {
    /**
     * Generate a random list of integers between Integer.MIN_VALUE and Integer.MAX_VALUE
     *
     * @param arraySize the size of the list
     * @return the list of integers
     */
    public static Comparable[] generateRandomIntegers(Random random, int arraySize) {
        Comparable[] data = new Comparable[arraySize];

        for (int i = 0; i < arraySize; i++) {
            data[i] = random.nextInt() * (Integer.MAX_VALUE - Integer.MIN_VALUE) + 1;
        }

        return data;
    }

    /**
     * Generate a random list of shorts between Short.MIN_VALUE and Short.MAX_VALUE
     *
     * @param random the random number generator
     * @param size   the size of the list
     * @return the list of integers
     */
    public static Comparable[] generateRandomShorts(Random random, int size) {
        Comparable[] data = new Comparable[size];
        for (int i = 0; i < size; i++) {
            data[i] = (short) (random.nextInt(Short.MAX_VALUE - Short.MIN_VALUE) + 1);
        }

        return data;
    }

    /**
     * Generate a random list of float between Float.MIN_VALUE and Float.MAX_VALUE
     *
     * @param size the size of the list
     * @return the list of floats
     */
    public static Comparable[] generateRandomFloats(Random random, int size) {
        Comparable[] data = new Comparable[size];

        for (int i = 0; i < size; i++) {
            data[i] = (random.nextFloat() * (Float.MAX_VALUE - Float.MIN_VALUE) + 1);
        }

        return data;
    }
    /**
     * Takes an array of strings and generates a random list size n
     *
     * @param random        the random number generator
     * @param size          the size of the list
     * @param sourceStrings the source strings
     * @return the list of strings
     */
    public static Comparable[] generateRandomStrings(Random random, int size, String[] sourceStrings) {
        Comparable[] data = new Comparable[size];


        for (int i = 0; i < size; i++) {
            data[i] = sourceStrings[random.nextInt(sourceStrings.length - 1)];
        }

        return data;
    }

    /**
     * Takes an array of strings and sorts it using quick sort
     *
     * @param size          the size of the list
     * @param sourceStrings the source strings
     * @return the list of strings
     */
    protected static Comparable[] sortStrings(int size, String[] sourceStrings) {
        Comparable[] data = new Comparable[size];
        System.arraycopy(sourceStrings, 0, data, 0, size);
        quickSort(data, 0, size - 1);
        return data;
    }
}
