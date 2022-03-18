package ch.usi.ee.experiments;

import static ch.usi.ee.data.DataGenerator.*;

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


public class Experiments {
    /**
     * This function executes the experiments.
     *
     * @param rand              The random number generator.
     * @param arraySizes        The array sizes to be tested.
     * @param totalIterations   The total number of iterations.
     * @param dataTypes         The data types to be tested.
     * @param dataOrderings     The data orderings to be tested.
     * @param stringsSourceFile The source file for the strings.
     * @throws IOException
     */
    public static void runExperiments(Random rand, int[] arraySizes, int totalIterations, DataType[] dataTypes, DataOrdering[] dataOrderings, String stringsSourceFile) throws IOException {
        int numberOfAlgorithms = 3;
        String[] filteredStrings = null;

        int[] randomIntegers = null, sortedIntegers = null, reversedIntegers = null;
        short[] randomShorts = null, sortedShorts = null, reversedShorts = null;
        float[] randomFloats = null, sortedFloats = null, reversedFloats = null;
        String[] randomStrings = null, sortedStrings = null, reversedStrings = null;

        Stack<Result> BubbleSortPassPerItemResults = new Stack<Result>();
        Stack<Result> BubbleSortUntilNoChangeResults = new Stack<Result>();
        Stack<Result> BubbleSortWhileNeededResults = new Stack<Result>();
        Stack<Result> results;

        Sorter[] sorters = new Sorter[numberOfAlgorithms];
        sorters[0] = new BubbleSortPassPerItem();
        sorters[1] = new BubbleSortUntilNoChange();
        sorters[2] = new BubbleSortWhileNeeded();

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

                        // Get the data to be sorted according to the type ordering and array size
                        switch (type) {
                            case INTEGER:
                                switch (ordering) {
                                    case RANDOM:
                                        randomIntegers = generateRandomIntegers(rand, size);
                                        System.out.println("  Random integers list of size " + size + "... \tOK");
                                        break;
                                    case SORTED:
                                        sortedIntegers = Sorters.quickSort(randomIntegers, 0, size - 1);
                                        System.out.println("  Sorted integers list of size " + size + "... \tOK");
                                        break;
                                    case REVERSED:
                                        reversedIntegers = Sorters.reverseArray(sortedIntegers, size);
                                        System.out.println("  Reversed ints list of size " + size + "... \tOK");
                                        break;
                                    default:
                                        System.out.println("Invalid ordering");
                                        System.exit(1);
                                        break;
                                }
                                break;
                            case SHORT:
                                switch (ordering) {
                                    case RANDOM:
                                        randomShorts = generateRandomShorts(rand, size);
                                        System.out.println("  Random shorts list of size " + size + "... \tOK");
                                        break;
                                    case SORTED:
                                        sortedShorts = Sorters.quickSort(randomShorts, 0, size - 1);
                                        System.out.println("  Sorted shorts list of size " + size + "... \tOK");
                                        break;
                                    case REVERSED:
                                        reversedShorts = Sorters.reverseArray(sortedShorts, size);
                                        System.out.println("  Reversed shorts list of size " + size + "... \tOK");
                                        break;
                                    default:
                                        System.out.println("Sorting order not supported");
                                        System.exit(1);
                                        break;
                                }
                                break;
                            case STRING:
                                switch (ordering) {
                                    case RANDOM:
                                        randomStrings = generateRandomStrings(rand, size, filteredStrings);
                                        System.out.println("  Random strings list of size " + size + "... \tOK");
                                        break;
                                    case SORTED:
                                        sortedStrings = (String[]) Sorters.quickSort(randomStrings, 0, size - 1);
                                        System.out.println("  Sorted strings list of size " + size + "... \tOK");
                                        break;
                                    case REVERSED:
                                        reversedStrings = Sorters.reverseArray(sortedStrings, size);
                                        System.out.println("  Reversed strings list of size " + size + "... \tOK");
                                        break;
                                    default:
                                        System.out.println("Invalid ordering");
                                        System.exit(1);
                                        break;
                                }
                                break;
                            case FLOAT:
                                switch (ordering) {
                                    case RANDOM:
                                        randomFloats = generateRandomFloats(rand, size);
                                        System.out.println("  Random floats list of size " + size + "... \tOK");
                                        break;
                                    case SORTED:
                                        sortedFloats = Sorters.quickSort(randomFloats, 0, size - 1);
                                        System.out.println("  Sorted floats list of size " + size + "... \tOK");
                                        break;
                                    case REVERSED:
                                        reversedFloats = Sorters.reverseArray(sortedFloats, size);
                                        System.out.println("  Reversed floats list of size " + size + "... \tOK");
                                        break;
                                    default:
                                        System.out.println("Invalid ordering");
                                        System.exit(1);
                                        break;
                                }
                                System.out.println("Type not implemented");
                                System.exit(1);
                                break;
                        }

                        for (int i = 0; i < totalIterations; i++) {
                            long startTime = System.nanoTime();
                            sorter.sort(randomStrings);
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