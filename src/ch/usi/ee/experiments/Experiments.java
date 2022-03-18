package ch.usi.ee.experiments;

import ch.usi.ee.bubble_sort.BubbleSortPassPerItem;
import ch.usi.ee.bubble_sort.BubbleSortUntilNoChange;
import ch.usi.ee.bubble_sort.BubbleSortWhileNeeded;
import ch.usi.ee.bubble_sort.Sorter;
import ch.usi.ee.data.Sorters;
import ch.usi.ee.enums.DataOrdering;
import ch.usi.ee.enums.DataType;

import java.io.IOException;
import java.util.Random;
import java.util.Stack;

import static ch.usi.ee.data.DataGenerator.*;
import static ch.usi.ee.enums.DataOrdering.*;
import static ch.usi.ee.enums.DataType.*;

public class Experiments {
    public static void runExperiments(Random rand) throws IOException {
        DataType[] dataTypes = {INTEGERS, FLOATS, SHORTS, STRINGS};
        DataOrdering[] dataOrderings = {SORTED, RANDOM, REVERSED};
        int[] arraySizes = {10, 100, 1000, 10000};
        int totalIterations = 1000;
        int numberOfAlgorithms = 3;
        String[] filteredStrings;

        Stack<Result> BubbleSortPassPerItemResults = new Stack<Result>();
        Stack<Result> BubbleSortUntilNoChangeResults = new Stack<Result>();
        Stack<Result> BubbleSortWhileNeededResults = new Stack<Result>();
        Stack<Result> results;

        Sorter[] sorters = new Sorter[numberOfAlgorithms];
        sorters[0] = new BubbleSortPassPerItem();
        sorters[1] = new BubbleSortUntilNoChange();
        sorters[2] = new BubbleSortWhileNeeded();
        int[] randomIntegers, sortedIntegers, reversedIntegers;
        short[] randomShorts, sortedShorts, reversedShorts;
        float[] randomFloats, sortedFloats, reversedFloats;
        String[] randomStrings, sortedStrings, reversedStrings;

        System.out.println("Generating data for the experiments...");
        for (int size : arraySizes) {
            randomIntegers = generateRandomIntegers(rand, size);
            System.out.println("  Random integers list of size " + size + "... OK");

            sortedIntegers = Sorters.quickSort(randomIntegers, 0, size - 1);
            System.out.println("  Sorted integers list of size " + size + "... OK");

            reversedIntegers = Sorters.reverseArray(sortedIntegers, size);
            System.out.println("  Reversed integers list of size " + size + "... OK");

            randomShorts = generateRandomShorts(rand, size);
            System.out.println("  Random shorts list of size " + size + "... OK");

            sortedShorts = Sorters.quickSort(randomShorts, 0, size - 1);
            System.out.println("  Sorted shorts list of size " + size + "... OK");

            reversedShorts = Sorters.reverseArray(sortedShorts, size);
            System.out.println("  Reversed shorts list of size " + size + "... OK");

            randomFloats = generateRandomFloats(rand, size);
            System.out.println("  Random floats list of size " + size + "... OK");

            sortedFloats = Sorters.quickSort(randomFloats, 0, size - 1);
            System.out.println("  Sorted floats list of size " + size + "... OK");

            reversedFloats = Sorters.reverseArray(sortedFloats, size);
            System.out.println("  Reversed floats list of size " + size + "... OK");

            String path = System.getProperty("user.dir");
            filteredStrings = readData(path + "/filtered_strings.txt");
            randomStrings = generateRandomStrings(rand, size, filteredStrings);
            System.out.println("  Random strings list of size " + size + "... OK");

            sortedStrings = (String[]) Sorters.quickSort(randomStrings, 0, size - 1);
            System.out.println("  Sorted strings list of size " + size + "... OK");

            reversedStrings = Sorters.reverseArray(sortedStrings, size);
            System.out.println("  Reversed strings list of size " + size + "... OK\n");
        }

        System.out.println("Data generation successful.\n");

        System.out.println("Running experiments...\n");
        for (Sorter sorter : sorters) {
            if (sorter instanceof BubbleSortPassPerItem) {
                results = BubbleSortPassPerItemResults;
            } else if (sorter instanceof BubbleSortUntilNoChange) {
                results = BubbleSortUntilNoChangeResults;
            } else {
                results = BubbleSortWhileNeededResults;
            }

            for (DataType type : dataTypes) {
                for (DataOrdering ordering : dataOrderings) {
                    for (int size : arraySizes) {
                        System.gc(); // Force garbage collection
                        Result iteration_result = new Result(type, ordering, sorter.getClass().getName(), size, totalIterations);

                        //array of type T
//                        T[] array = null;
//                        T[] data;
//                        switch (type) {
//                            case INTEGERS:
//                                switch (ordering) {
//                                    case RANDOM:
//                                        data = randomIntegers;
//                                }
//                        }
                        for (int i = 0; i < totalIterations; i++) {
                            long startTime = System.nanoTime();
//                            sorter.sort(randomStrings); // TODO: Implement sorting
                            long endTime = System.nanoTime();
                            long elapsedTime = endTime - startTime;

                            iteration_result.addElapsedTime(i, elapsedTime);
                            results.push(iteration_result);
                        }

                        results.push(iteration_result);
                    }
                }
            }
        }
        System.out.println("Experiments completed.\n");
    }
}