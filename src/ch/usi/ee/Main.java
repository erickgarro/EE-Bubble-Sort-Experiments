package ch.usi.ee;

import java.io.IOException;
import java.util.*;

import ch.usi.ee.data.DataGenerator;
import ch.usi.ee.enums.DataOrdering;
import ch.usi.ee.enums.DataType;
import ch.usi.ee.io.Serializer;

import static ch.usi.ee.data.StringsProcessor.prepareStrings;
import static ch.usi.ee.enums.DataOrdering.*;
import static ch.usi.ee.enums.DataType.*;
import static ch.usi.ee.experiments.Experiments.runExperiments;
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
        int stringLength = 10;
        int lastNumberOfIterations = 5;
        Long seed;
        Random rand = new Random();

        System.out.println("Java Bubble Sort Experiments");
        System.out.println("by Erick Garro Elizondo and Cindy Guerrero Toro.\n");


        // Parse arguments
        if (args.length == 1 && args[0].equals("-h")) {
            printProgramUsage();
            exit(0);
        } else if (args.length > 0) {
            System.out.println("Checking for arguments and setting variables...\n");

            // Grab all the command line arguments and put them in a map
            for (int i = 0; i < args.length; i++) {
                String arg = args[i];
                if (arg.startsWith("-")) {
                    if (i + 1 < args.length && !args[i + 1].startsWith("-")) {
                        try {
                            if (arg.equals("-i") || arg.equals("-s") || arg.equals("-l") || arg.equals("-a") || arg.equals("-n")) {
                                Stack<Long> argValues = new Stack<>();
                                if (arg.equals("-n") && Long.parseLong(args[i + 1]) < 5) {
                                    System.out.println("The number of number of iterations to generate statistics must be greater than 5");
                                    exit(1);
                                }
                                if (arg.equals("-a")) {
                                    String[] arraySizesValues = args[i + 1].split(",");
                                    for (String value : arraySizesValues) {
                                        Long parsedValue = Long.parseLong(value);
                                        if (parsedValue >= 5) {
                                            argValues.push(parsedValue);
                                        } else {
                                            System.out.println("Array size must be at least 5");
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
            System.out.println("  Array sizes set to: " + arraySizes);
        } else {
            arraySizes.push(10L);
            arraySizes.push(100L);
            arraySizes.push(1000L);
            arraySizes.push(10000L);
            System.out.println("  Array sizes set to default value: " + arraySizes);
        }

        // If the user has specified a value for the string length, use it
        if (arguments.containsKey("-l")) {
            stringLength = Integer.parseInt(arguments.get("-l").get(0).toString());
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
        System.out.print("  Data Types available: {");
        for (int i = 0; i < dataTypes.length; i++) {
            System.out.print(dataTypes[i].toString());
            if (i < arraySizes.size()) {
                System.out.print(", ");
            } else {
                System.out.println("}");
            }
        }

        System.out.print("  Data Orderings available: {");
        for (int i = 0; i < dataOrderings.length; i++) {
            System.out.print(dataOrderings[i]);
            if (i < arraySizes.size() - 1) {
                System.out.print(", ");
            } else {
                System.out.println("}");
            }
        }

        System.out.println("  Number of data orderings available: " + dataOrderings.length);
        System.out.println("  Number of Bubble Sort algorithms available: " + 3 + "\n");

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
        Stack<Object> results = runExperiments(rand, arraySizes, totalIterations, dataTypes, dataOrderings, filteredStringsFilePath);
        Serializer.serializeResults(results);

        // Generate the statistics


        // TODO: 19.03.22 - Implement the results summary printing and saving to a TXT or CSV file
    }

    private static void printProgramUsage() {
        System.out.println("Java Bubble Sort Experiments\n");
        System.out.println("Usage: java -jar <program name>.jar [-h] [-i <iterations>] [-s <seed>] [-l <string length>]\n");
        System.out.println("\t-i <iterations>\t\tSets the number of iterations to perform. Default is 1000.");
        System.out.println("\t-s <seed>\t\t\tSets the seed for the random number generator. Default is random.");
        System.out.println("\t-l <string length>\tSets the length of the strings to be used. Default is 10.");
        System.out.println("\t-a <array size>\t\tSets the size of the array to be used, separated by commas and no spaces. Default is 10,100,1000,10000.");
        System.out.println("\t-n <number of iterations>\tSets the number of iterations take into account to generate statistics. Default is 5.");
        System.out.println("\t-h\t\t\t\t\tPrints this help message.");
        System.out.println("\nCredits: Created by Erick Garro Elizondo and Cindy Guerrero Toro.");
        System.out.println("\nWith a hand of GitHub Copilot ;-)");
    }
}