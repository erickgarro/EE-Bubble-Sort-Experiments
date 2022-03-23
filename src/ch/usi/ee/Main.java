/**
 * Experimentation & Evaluation SP2022
 * USI - Università della Svizzera italiana
 * Project: Java Bubble Sort Experiments
 *
 * Authors: Erick Garro Elizondo & Cindy Guerrero Toro
 */

package ch.usi.ee;

import java.io.IOException;
import java.util.*;

import ch.usi.ee.data.DataGenerator;
import ch.usi.ee.enums.Algorithm;
import ch.usi.ee.enums.DataOrdering;
import ch.usi.ee.enums.DataType;
import ch.usi.ee.statistics.Result;
import ch.usi.ee.statistics.Statistics;

import static ch.usi.ee.data.StringsProcessor.*;
import static ch.usi.ee.enums.DataOrdering.*;
import static ch.usi.ee.enums.DataType.*;
import static ch.usi.ee.experiments.Experiments.*;
import static ch.usi.ee.io.CreateCSV.generateCSV;
import static ch.usi.ee.statistics.StatisticsGenerator.*;
import static java.lang.System.exit;


public class Main extends DataGenerator {
    public static void main(String[] args) throws IOException {
        String workingDirectory = System.getProperty("user.dir");
        String path = workingDirectory + "/data/";
        String rawStringsFile = path + "all_strings.txt";
        String filteredStringsFile = "filtered_strings_";
        Map<String, Stack<Long>> arguments = new HashMap<>();
        DataType[] dataTypes = {INTEGER, FLOAT, SHORT, STRING};
        DataOrdering[] dataOrderings = {ASC, RANDOM, DESC};
        Stack<Long> arraySizes = new Stack<>();
        int totalIterations = 1000;
        int stringLength = 5;
        int lastNumberOfIterations = 30;
        Long seed;
        Random rand = new Random();
        boolean printReports = false;

        // Parse arguments
        if (args.length == 1 && args[0].equals("-h")) {
            printProgramUsage();
            exit(0);
        } else if (args.length > 0) {
            System.out.println("Java Bubble Sort Experiments");
            System.out.println("by Erick Garro Elizondo and Cindy Guerrero Toro.\n");
            System.out.println("Checking for arguments and setting variables...\n");

            // Grab all the command line arguments and put them in a map
            for (int i = 0; i < args.length; i++) {
                String arg = args[i];
                if (arg.startsWith("-")) {
                    if (arg.equals("-r")) {
                        printReports = true;
                    }

                    if (i + 1 < args.length && !args[i + 1].startsWith("-")) {
                        try {
                            if (arg.equals("-i") || arg.equals("-s") || arg.equals("-l") || arg.equals("-a") || arg.equals("-n")) {
                                Stack<Long> argValues = new Stack<>();
                                if (arg.equals("-n") && Long.parseLong(args[i + 1]) < 30) {
                                    System.out.println("The number of number of iterations to generate statistics must be greater than 30");
                                    exit(1);
                                }

                                if (arg.equals("-a")) {
                                    String[] arraySizesValues = args[i + 1].split(",");
                                    for (String value : arraySizesValues) {
                                        Long parsedValue = Long.parseLong(value);
                                        if (parsedValue >= 10) {
                                            argValues.push(parsedValue);
                                        } else {
                                            System.out.println("Array size must be at least 10");
                                            exit(1);
                                        }
                                    }
                                } else {
                                    argValues.push(Long.parseLong(args[i + 1]));
                                }
                                arguments.put(arg, argValues);
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid argument value: " + args[i + 1]);
                            System.out.println("Execute program with flag -h to see usage.");
                            exit(1);
                        }
                    }
                }
            }
        }

        System.out.println("Experiment settings:");

        // If the user has specified a value for the string length, use it
        if (arguments.containsKey("-s")) {
            seed = arguments.get("-s").get(0);
            rand = new Random(seed);
            System.out.println("  Seed set to: " + seed);
        } else {
            System.out.println("  Seed set to random value.");
        }

        // If the user has specified a value for the number of iterations, use it
        if (arguments.containsKey("-i")) {
            totalIterations = Integer.parseInt(arguments.get("-i").get(0).toString());
            System.out.println("  Total iterations set to: " + totalIterations);
        } else {
            System.out.println("  Total iterations set to default value: " + totalIterations);
        }

        // If the user has specified a value for the array size, use it
        if (arguments.containsKey("-a")) {
            arraySizes = arguments.get("-a");
        } else {
            arraySizes.push(100L);
            arraySizes.push(1000L);
            arraySizes.push(10000L);
            System.out.println("  Array sizes set to default value: ");
        }

        System.out.println("  Array sizes set to: ");
        for (Long size : arraySizes) {
            System.out.println("   - " + size);
        }

        // If the user has specified a value for the string length, use it
        if (arguments.containsKey("-l")) {
            stringLength = Integer.parseInt(arguments.get("-l").get(0).toString());
            if(stringLength < 1) {
                System.out.println("String length must be greater than 1");
                exit(1);
            }
            System.out.println("  String length set to: " + stringLength);
        } else {
            System.out.println("  String length set to default value: " + stringLength);
        }

        // If the user has specified a value for the number of iterations to generate the statistics, use it
        if (arguments.containsKey("-n")) {
            lastNumberOfIterations = Integer.parseInt(arguments.get("-n").get(0).toString());
            System.out.println("  Number of iterations to generate statistics set to: " + lastNumberOfIterations);
        }

        // Print other variable information
        System.out.println("  Data Types available: ");
        for (int i = 0; i < dataTypes.length; i++) {
            System.out.println("   - " + dataTypes[i].toString());
        }

        System.out.println("  Data Orderings available: ");
        for (int i = 0; i < dataOrderings.length; i++) {
            System.out.println("   - " + dataOrderings[i]);
        }

        System.out.println("  Algorithms available to experiment: ");
        for (Algorithm algorithm : Algorithm.values()) {
            System.out.println("   - " + algorithm.toString());
        }

        // Look for the strings source files
        if (!new java.io.File(path + filteredStringsFile + stringLength + ".txt").exists()) {
            if (!new java.io.File(rawStringsFile).exists()) {
                System.out.println("The file " + rawStringsFile + " does not exist.\n");
                System.out.println("This program requires a TXT file with a list of strings of multiple length, that will be filtered and used for the experiments.");
                System.out.println("The file should be located in the data folder data and should contain one string per line\n");
                System.out.println("Make sure to store it in the folder <root to program>/data/");
                exit(1);
            } else {
                System.out.println("Preparing strings file for the experiments. Filtering strings with length: " + stringLength);

                if (prepareStrings(rawStringsFile, stringLength)) {
                    System.out.println("String data preparation successful\n");
                } else {
                    System.out.println("String data preparation failed.");
                    System.out.println("Invoke the program again with another string size value.\n");
                    System.exit(1);
                }
            }
        }

        // Execute the experiments
        String filteredStringsFilePath = path + filteredStringsFile + stringLength + ".txt";
        Stack<Result>[] results = runExperiments(rand, arraySizes, totalIterations, dataTypes, dataOrderings, filteredStringsFilePath);

        // Generate statistics
        Stack<Statistics> statistics = calculateStats(results, arraySizes, lastNumberOfIterations);

        if (printReports) {
            printStatistics(statistics);
        }

        System.out.println("Experiments finished.");
        generateCSV(statistics, workingDirectory);
    }

    private static void printProgramUsage() {
        System.out.println("Java Bubble Sort Experiments\n");
        System.out.println("Usage: java -jar <program name>.jar [-h] [-i <iterations>] [-s <seed>] [-l <string length>] [-a <array sizes>] [-n <last number of results>] [-r]");
        System.out.println("\t-i <iterations>\t\t\t\tSets the number of iterations to perform. Default is 1000.");
        System.out.println("\t-s <seed>\t\t\t\t\tSets the seed for the random number generator. Default is random.");
        System.out.println("\t-l <string length>\t\t\tSets the length of the strings to be used. Default is 5.");
        System.out.println("\t-a <array size>\t\t\t\tSets the size of the array to be used, separated by commas and no spaces. Default is 100,1000,10000.");
        System.out.println("\t-n <last number of results>\tSets the last n results to generate statistics. Default is 30.");
        System.out.println("\t-r\t\t\t\t\t\t\tPrints all the experiment results in the console.");
        System.out.println("\t-h\t\t\t\t\t\t\tPrints this help message.");
        System.out.println("\nIMPORTANT: The program requires a file named \"allstrings.txt\" with a list of strings of multiple length, that will be filtered\n" +
                "           and used for the experiments. It should be located in the data folder \"data\\\" and should contain one string per line.");
        System.out.println("\nCredits: Created by Erick Garro Elizondo and Cindy Guerrero Toro.");
        System.out.println("With a hand of GitHub Copilot ;-)");
    }
}