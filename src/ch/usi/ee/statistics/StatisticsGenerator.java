/**
 * Experimentation & Evaluation SP2022
 * USI - Università della Svizzera italiana
 * Project: Java Bubble Sort Experiments
 * <p>
 * Authors: Erick Garro Elizondo & Cindy Guerrero Toro
 */

package ch.usi.ee.statistics;

import java.util.Arrays;
import java.util.Stack;

public class StatisticsGenerator {
    /**
     * Calculate the statistics from the results of the experiments
     *
     * @param allResults             The Long array of results
     * @param arraySizes             The array of sizes
     * @param lastNumberOfIterations The number of iterations
     */
    public static Stack<Statistics> calculateStats(Stack<Result>[] allResults, Stack<Long> arraySizes, int lastNumberOfIterations) {
        Stack<Statistics> statistics = new Stack<>();

        for (Stack<Result> algorithmResults : allResults) {
            for (Result perAlgoResults : algorithmResults) {
                Long[] lastNResults = perAlgoResults.getLastNResults(lastNumberOfIterations).clone();
                Long[] lastNResultsSorted = perAlgoResults.getLastNResults(lastNumberOfIterations).clone();
                Statistics stats = new Statistics();

                stats.setAlgorithm(perAlgoResults.getAlgorithm().toString());
                stats.setDataType(perAlgoResults.getDataType().toString());
                stats.setDataOrdering(perAlgoResults.getDataOrdering().toString());
                stats.setArraySize(perAlgoResults.getArraySize());
                stats.setIterations(perAlgoResults.getIterations());
                stats.setFirstResult(perAlgoResults.getFirstResult());
                stats.setLastResult(perAlgoResults.getLastResult());
                stats.setLastNumberOfIterations(lastNumberOfIterations);
                stats.setLastNResults(lastNResults);
                stats.setLastNResultsSorted(lastNResultsSorted);
                stats.setMean(calculateMean(lastNResultsSorted));
                stats.setMedian(calculateMedian(lastNResultsSorted));
                stats.setStandardDeviation(calculateStandardDeviation(lastNResultsSorted, stats.getMean()));
                stats.setFirstQuartile(calculateFirstQuartile(lastNResultsSorted));
                stats.setThirdQuartile(calculateThirdQuartile(lastNResultsSorted));
                stats.setMinResult(perAlgoResults.getMinResult());
                stats.setMaxResult(perAlgoResults.getMaxResult());
                stats.setStandardError(calculateStandardError(lastNResultsSorted, stats.getMean()));
                stats.setInterQuartileRange(stats.getThirdQuartile() - stats.getFirstQuartile());

                statistics.push(stats);
            }
        }
        return statistics;
    }

    /**
     * Calculate the median given a Long array of results
     *
     * @param results The Long array of results
     * @return The median of the results
     */
    static double calculateMedian(Long[] results) {
        Arrays.sort(results);
        return results[results.length / 2];
    }

    /**
     * Calculate the mean given a Long array of results
     *
     * @param results The Long array of results
     * @return The mean of the results
     */
    static double calculateMean(Long[] results) {
        double sum = 0;
        for (Long result : results) {
            sum += result;
        }
        return sum / results.length;
    }

    /**
     * Calculate the standard deviation given a Long array of results
     *
     * @param results The Long array of results
     * @param mean    The mean of the results
     * @return the standard deviation
     */
    static double calculateStandardDeviation(Long[] results, double mean) {
        double sum = 0;
        for (Long result : results) {
            sum += Math.pow(result - mean, 2);
        }
        return Math.sqrt(sum / results.length);
    }

    /**
     * Calculate the standard error given a Long array of results
     * @param results The Long array of results
     * @param mean  The mean of the results
     * @return The standard error
     */
    static double calculateStandardError(Long[] results, double mean) {
        return calculateStandardDeviation(results, mean) / Math.sqrt(results.length);
    }


    /**
     * Calculate the first quartile given a Long array of results
     *
     * @param results The Long array of results
     * @return the first quartile
     */
    static double calculateFirstQuartile(Long[] results) {
        Arrays.sort(results);
        return results[results.length / 4];
    }

    /**
     * Calculate the third quartile given a Long array of results
     *
     * @param _results The Long array of results
     * @return the third quartile
     */
    static double calculateThirdQuartile(Long[] _results) {
        Long[] results = _results.clone();
        Arrays.sort(results);
        return results[results.length * 3 / 4];
    }

    /**
     * Calculate interquartile range given a Long array of results
     *
     * @param _results The Long array of results
     * @return the interquartile range
     */
    static double calculateInterquartileRange(Long[] _results) {
        Long[] results = _results.clone();
        Arrays.sort(results);
        return calculateThirdQuartile(results) - calculateFirstQuartile(results);
    }

    /**
     *  This function prints the statistics for each result
     *  @param statistics The statistics to print
     */
    public static void printStatistics(Stack<Statistics> statistics) {
        System.out.println("RESULTS SUMMARY:");

        for (int i = 0; i < statistics.size(); i++) {
            System.out.println("  Algorithm: " + statistics.get(i).getAlgorithm());
            System.out.println("  Data Type: " + statistics.get(i).getDataType());
            System.out.println("  Data Ordering: " + statistics.get(i).getDataOrdering());
            System.out.println("  Array size: " + statistics.get(i).getArraySize());
            System.out.println("  Standard deviation [ns]: " + statistics.get(i).getStandardDeviation());
            System.out.println("  Standard error [ns]: " + statistics.get(i).getStandardError());
            System.out.println("  Mean [ns]: " + statistics.get(i).getMean());
            System.out.println("  Minimum time [ns]: " + statistics.get(i).getMinResult());
            System.out.println("  Maximum time [ns]: " + statistics.get(i).getMaxResult());
            System.out.println("  Median [ns]: " + statistics.get(i).getMedian());
            System.out.println("  Number of iterations: " + statistics.get(i).getIterations());
            System.out.println("  First quartile [ns]: " + statistics.get(i).getFirstQuartile());
            System.out.println("  Third quartile [ns]: " + statistics.get(i).getThirdQuartile());
            System.out.println("  Interquartile range [ns]: " + statistics.get(i).getInterQuartileRange());
            System.out.println("  First result [ns]: " + statistics.get(i).getFirstResult());
            System.out.println("  Last result [ns]: " + statistics.get(i).getLastResult() + "\n");
        }
    }
}
