package ch.usi.ee;

import ch.usi.ee.BubbleSort.BubbleSortPassPerItem;
import ch.usi.ee.BubbleSort.BubbleSortUntilNoChange;
import ch.usi.ee.BubbleSort.BubbleSortWhileNeeded;
import ch.usi.ee.BubbleSort.Sorter;

import java.util.*;

public class Experiments {
    public static int total = 10000; //we change this size each time we run each code (1,10,100,1000,10000)
    //public static int total = (int) Math.pow(2,10); // Before overflow

    public static void main(String[] args) {

        for (int i = 0; i < 1000; i++) {
            //Integer[] array = sortedArray(total);
            //Integer[] array = reverseSortedArray(total);
            Integer[] array = randomArray(total);

            Sorter<Integer> bubbleSortPassPerItem = new BubbleSortPassPerItem<Integer>();
            timeSorter(bubbleSortPassPerItem, array);

            Sorter<Integer> bubbleSortWhileNeeded = new BubbleSortWhileNeeded<>();
            timeSorter(bubbleSortWhileNeeded, array);

            Sorter<Integer> bubbleSortUntilNoChange = new BubbleSortUntilNoChange<>();
            timeSorter(bubbleSortUntilNoChange, array);
        }
    }

    private static void timeSorter(Sorter<Integer> sorter, Integer[] array) {
        System.out.println(sorter.getClass().toString());
        long startTime = System.nanoTime(); //Start time
        sorter.sort(array);
        long endTime = System.nanoTime(); //End time
        System.out.println(startTime);
        System.out.println(endTime);
        System.out.println((endTime - startTime) / total);
    }

    // Sorted array [0, 1, ..., n-1]
    private static Integer[] sortedArray(int max) {
        Integer[] array = new Integer[max];
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
        return array;
    }

    // Reverse sorted array [n-1, ... 1, 0]
    private static Integer[] reverseSortedArray(int max) {
        Integer[] array = new Integer[max];
        for (int i = array.length - 1; i >= 0; i--) {
            array[i] = i;
        }
        return array;
    }

    // Random array (size=n; range: 0 to n-1)
    private static Integer[] randomArray(int total) {
        Random rand = new Random();
        Integer[] array = new Integer[total];
        for (int i = 0; i < array.length; i++) {
            array[i] = rand.nextInt(total);
        }
        // print array
//        for (int element: array) {
//            System.out.println(array[element]);
//        }
        return array;
    }


//    @TODO (2) : create other functions as modified from ^^^^^
//             (implemented functions: ordered, reverse-ordered, random) to consider data types:
//           We may be able to set an initial length (which will not be modified)
//            * Char (use '\u0000' to increase the int by 1 ... ['\u0000', '\u0001', ...])
//            * Float
//            * Short (this one we basically reuse (1)))


// this is for later for measuring the time that takes to run the code.
// we do not need it to build each of the arrays. Since we do not care that part.

//    public static void testTime (int max) {
//        int[] sortedArray = new int[max];
//        for(int i=0; i<sortedArray.length; i++) {
//            long startTime = System.nanoTime();
//            sortedArray[i]=i; // add here the method to be measured
//            long endTime = System.nanoTime();
//            System.out.println(endTime-startTime); // to measure loop
//        }
//    }

}