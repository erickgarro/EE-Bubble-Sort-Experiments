package ch.usi.ee;

import java.io.FileNotFoundException;
import java.util.*;

import ch.usi.ee.BubbleSort.BubbleSortPassPerItem;
import ch.usi.ee.BubbleSort.BubbleSortUntilNoChange;
import ch.usi.ee.BubbleSort.BubbleSortWhileNeeded;
import ch.usi.ee.BubbleSort.Sorter;
import ch.usi.ee.enums.DataOrdering;
import ch.usi.ee.enums.DataType;
import ch.usi.ee.io.FileReader;

import static ch.usi.ee.enums.DataOrdering.*;
import static ch.usi.ee.enums.DataType.*;


public class Experiments extends FileReader {
    public static void main(String[] args) throws FileNotFoundException {
        DataType dataTypes[] = {INTEGERS, FLOATS, SHORTS, STRINGS};
        DataOrdering dataOrderings[] = {SORTED, RANDOM, REVERSED};
        int[] arraySizes = {10, 100, 1000, 10000};
        int totalIterations = 1000;
        int numberOfAlgorithms = 3;
        FileReader fileReader;

        // Initialize sorters
        Sorter[] sorters = new Sorter[numberOfAlgorithms];
        sorters[0] = new BubbleSortPassPerItem();
        sorters[1] = new BubbleSortUntilNoChange();
        sorters[2] = new BubbleSortWhileNeeded();

        // Results of the experiments per data type
        Stack<Result> integersResults = new Stack<>();
        Stack<Result> shortsResults = new Stack<>();
        Stack<Result> floatsResults = new Stack<>();
        Stack<Result> stringsResults = new Stack<>();

        for (DataType type : dataTypes) {
            for (int i = 0; i < arraySizes.length; i++) {
                for (DataOrdering ordering : dataOrderings) {
                    for (Sorter sorter : sorters) {
                        fileReader = new FileReader();
                        Comparable[] data = fileReader.readData(type, ordering, arraySizes[i]);

                        for (int j = 0; j < totalIterations; j++) {
                            long startTime = System.nanoTime();
                            sorter.sort(data);
                            long endTime = System.nanoTime();
                            long elapsedTime = endTime - startTime;
                        }
                    }
                }
            }
        }
    }
}
