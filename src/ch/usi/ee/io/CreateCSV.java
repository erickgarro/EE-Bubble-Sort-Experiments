/**
 * Experimentation & Evaluation SP2022
 * USI - Università della Svizzera italiana
 * Project: Java Bubble Sort Experiments
 *
 * Authors: Erick Garro Elizondo & Cindy Guerrero Toro
 */

package ch.usi.ee.io;

import ch.usi.ee.statistics.Statistics;

import java.io.IOException;
import java.util.Stack;

import static ch.usi.ee.io.FileWriter.getDateTime;
import static ch.usi.ee.io.FileWriter.writeData;

public class CreateCSV {
    /**
     * Generates a CSV file with the results of the experiments.
     *
     * @param statistics       The statistics object containing the results.
     * @param workingDirectory The directory where the CSV file will be created.
     */
    public static void generateCSV(Stack<Statistics> statistics, String workingDirectory) throws IOException {
        String csvFile = getDateTime() + "_results.csv";
        String csvPath = workingDirectory + "/results/" + csvFile;
        String csvHeader = "Algorithm,Data Type,Data Ordering,Array size,Standard deviation,Mean,Minimum time,Maximum time,Median,Number of iterations,First quartile,Third quartile,First result,Last result";
        String csvLine = "";
        Stack<String> csvData = new Stack<>();

        csvData.push(csvHeader + "\n");

        try {
            for (Statistics stat : statistics) {
                csvLine = stat.getAlgorithm() + "," + stat.getDataType() + "," +
                        stat.getDataOrdering() + "," + stat.getArraySize() + "," + stat.getStandardDeviation() + "," +
                        stat.getMean() + "," + stat.getMinResult() + "," + stat.getMaxResult() + "," +
                        stat.getMedian() + "," + stat.getLastNumberOfIterations() + "," + stat.getFirstQuartile() + "," +
                        stat.getThirdQuartile() + "," + stat.getFirstResult() + "," + stat.getLastResult();
                csvData.push(csvLine + "\n");
            }
        } catch (Exception e) {
            System.out.println("Error while generating CSV file.");
            throw e;
        }

        writeData(csvData, csvPath);
        System.out.println("CSV results file created in " + csvPath);
    }
}
