/**
 * Experimentation & Evaluation SP2022
 * USI - Università della Svizzera italiana
 * Project: Java Bubble Sort Experiments
 *
 * Authors: Erick Garro Elizondo & Cindy Guerrero Toro
 */

package ch.usi.ee.experiments;

import static ch.usi.ee.data.DataGenerator.*;
import static ch.usi.ee.enums.Algorithm.*;
import static java.lang.System.exit;

import ch.usi.ee.bubble_sort.BubbleSortPassPerItem;
import ch.usi.ee.bubble_sort.BubbleSortUntilNoChange;
import ch.usi.ee.bubble_sort.BubbleSortWhileNeeded;
import ch.usi.ee.bubble_sort.Sorter;
import ch.usi.ee.data.Sorters;
import ch.usi.ee.enums.DataOrdering;
import ch.usi.ee.enums.DataType;
import ch.usi.ee.statistics.Result;

import java.io.IOException;
import java.util.Random;
import java.util.Stack;


public class Experiments {
    /**
     * This function executes the experiments and returns the results.
     *
     * @param rand              The random number generator.
     * @param arraySizes        The array sizes to be tested.
     * @param totalIterations   The total number of iterations.
     * @param dataTypes         The data types to be tested.
     * @param dataOrderings     The data orderings to be tested.
     * @param stringsSourceFile The source file for the strings to be selected randomly.
     * @throws IOException
     * return The results of the experiments.
     * @return
     */
    public static Stack<Result>[] runExperiments(Random rand, Stack<Long> arraySizes, int totalIterations, DataType[] dataTypes, DataOrdering[] dataOrderings, String stringsSourceFile) throws IOException {
        int numberOfAlgorithms = 3;
        String[] filteredStrings = readData(stringsSourceFile);

        Stack<Result> BubbleSortPassPerItemResults = new Stack<Result>();
        Stack<Result> BubbleSortUntilNoChangeResults = new Stack<Result>();
        Stack<Result> BubbleSortWhileNeededResults = new Stack<Result>();
        Stack<Result>[] allResults = new Stack[numberOfAlgorithms];
        Stack<Result> results;

        Sorter[] sorters = new Sorter[numberOfAlgorithms];
        sorters[0] = new BubbleSortPassPerItem();
        sorters[1] = new BubbleSortUntilNoChange();
        sorters[2] = new BubbleSortWhileNeeded();

        System.out.println("Running experiments...\n");

        Comparable[] randomIntegers = null, sortedIntegers = null, reversedIntegers = null;
        Comparable[] randomShorts = null, sortedShorts = null, reversedShorts = null;
        Comparable[] randomFloats = null, sortedFloats = null, reversedFloats = null;
        Comparable[] randomStrings = null, sortedStrings = null, reversedStrings = null;
        Comparable[] toSort = null;

        for (DataType type : dataTypes) {
            for (Long arraySize : arraySizes) {
                int size = arraySize.intValue();
                randomIntegers = generateRandomIntegers(rand, size);
                randomShorts = generateRandomShorts(rand, size);
                randomFloats = generateRandomFloats(rand, size);
                randomStrings = generateRandomStrings(rand, size, filteredStrings);

                for (DataOrdering ordering : dataOrderings) {
                    // Get the data to be sorted according to the type ordering and array size
                    switch (type) {
                        case INTEGER:
                            switch (ordering) {
                                case RANDOM:
                                    toSort = randomIntegers.clone();
                                    break;
                                case ASC:
                                    sortedIntegers = (Comparable[]) Sorters.quickSort(randomIntegers.clone(), 0, size - 1);
                                    toSort = sortedIntegers.clone();
                                    break;
                                case DESC:
                                    reversedIntegers = Sorters.reverseArray(sortedIntegers.clone(), size).clone();
                                    toSort = reversedIntegers.clone();
                                    break;
                                default:
                                    System.out.println("Invalid ordering");
                                    exit(1);
                                    break;
                            }
                            break;
                        case SHORT:
                            switch (ordering) {
                                case RANDOM:
                                    toSort = randomShorts.clone();
                                    break;
                                case ASC:
                                    sortedShorts = (Comparable[]) Sorters.quickSort(randomShorts.clone(), 0, size - 1);
                                    toSort = sortedShorts.clone();
                                    break;
                                case DESC:
                                    reversedShorts = Sorters.reverseArray(sortedShorts.clone(), size).clone();
                                    toSort = reversedShorts.clone();
                                    break;
                                default:
                                    System.out.println("Sorting order not supported");
                                    exit(1);
                                    break;
                            }
                            break;
                        case FLOAT:
                            switch (ordering) {
                                case RANDOM:
                                    toSort = randomFloats.clone();
                                    break;
                                case ASC:
                                    sortedFloats = (Comparable[]) Sorters.quickSort(randomFloats.clone(), 0, size - 1);
                                    toSort = sortedFloats.clone();
                                    break;
                                case DESC:
                                    reversedFloats = Sorters.reverseArray(sortedFloats, size).clone();
                                    toSort = reversedFloats.clone();
                                    break;
                                default:
                                    System.out.println("Invalid ordering");
                                    exit(1);
                                    break;
                            }
                            break;
                        case STRING:
                            switch (ordering) {
                                case RANDOM:
                                    toSort = randomStrings.clone();
                                    break;
                                case ASC:
                                    sortedStrings = (Comparable[]) Sorters.quickSort(randomStrings.clone(), 0, size - 1);
                                    toSort = sortedStrings.clone();
                                    break;
                                case DESC:
                                    reversedStrings = Sorters.reverseArray(sortedStrings.clone(), size).clone();
                                    toSort = reversedStrings.clone();
                                    break;
                                default:
                                    System.out.println("Invalid ordering");
                                    exit(1);
                                    break;
                            }
                            break;
                        default:
                            System.out.println("Type not implemented");
                            exit(1);
                            break;
                    }

                    int allResultsArrayIndex = 0;
                    for (Sorter sorter : sorters) {
                        String className = sorter.getClass().getName().split("\\.")[sorter.getClass().getName().split("\\.").length - 1];
                        Result iteration_result = new Result(type, ordering, className, size, totalIterations);

                        if (sorter instanceof BubbleSortPassPerItem) {
                            iteration_result.setAlgorithm(BUBBLE_SORT_PASS_PER_ITEM);
                            results = BubbleSortPassPerItemResults;
                        } else if (sorter instanceof BubbleSortUntilNoChange) {
                            iteration_result.setAlgorithm(BUBBLE_SORT_UNTIL_NO_CHANGE);
                            results = BubbleSortUntilNoChangeResults;
                        } else {
                            iteration_result.setAlgorithm(BUBBLE_SORT_WHILE_NEEDED);
                            results = BubbleSortWhileNeededResults;
                        }

                        System.gc(); // Force garbage collection

                        // Run the sorting algorithms
                        for (int i = 0; i < totalIterations; i++) {
                            long startTime = System.nanoTime();
                            sorter.sort(toSort.clone());
                            long endTime = System.nanoTime();
                            long elapsedTime = endTime - startTime;
                            iteration_result.addElapsedTime(i, elapsedTime);
                        }
                        results.push(iteration_result);
                        allResults[allResultsArrayIndex++] = results;
                    }
                }
            }
        }

        System.out.println("Experiments finished.");
        return allResults;
    }
}