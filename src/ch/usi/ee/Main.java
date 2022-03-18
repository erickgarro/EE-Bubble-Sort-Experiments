package ch.usi.ee;

import java.io.IOException;
import java.util.*;

import ch.usi.ee.data.DataGenerator;
import ch.usi.ee.data.StringsPreparator;

import static ch.usi.ee.experiments.Experiments.runExperiments;


public class Main extends DataGenerator {
    public static void main(String[] args) throws IOException {
        String path = System.getProperty("user.dir");
        String rawStringsFile = path + "/words_alpha.txt";
        int stringLength = 10;
        Random rand = new Random();

        if (args.length > 0 && args[0].equals("-init")) {
            if (args.length == 2) {
                try {
                    stringLength = Integer.parseInt(args[1]);
                } catch (NumberFormatException e) {
                    System.out.println("The length must be an integer.");
                    System.out.println("Use the flag -help to see the usage.");
                    System.exit(1);
                }

                if (stringLength == 3) {
                    rawStringsFile = args[2];
                }
            }

            System.out.println("Reading file and filtering strings of length " + stringLength + "...");

            if (StringsPreparator.prepareStrings(rawStringsFile, stringLength)) {
                System.out.println("String data preparation successful.");
                System.out.println("You can now run the experiments.");
                System.exit(0);
            } else {
                System.out.println("String data preparation failed.");
                System.exit(1);
            }
        } else if (args.length > 1 && args[0].equals("-seed")) {
            if (args.length == 2) {
                try {
                    rand = new Random(Long.parseLong(args[1]));
                } catch (NumberFormatException e) {
                    System.out.println("The seed must be an integer.");
                    System.out.println("Use the flag -help to see the usage.");
                    System.exit(1);
                }
            }
            System.out.println("Using provided seed: " + args[1]);
        } else if (args.length == 1 && args[0].equals("-fixed-seed")) {
            long seed = 17072021;
            rand = new Random(seed);
            System.out.println("Using fixed seed: " + seed);
        } else if (args.length == 1 && args[0].equals("-help")) {
            printProgramUsage();
            System.exit(0);
        } else {
            System.out.println("Using system generated random seed.");
        }

        runExperiments(rand);
    }

    private static void printProgramUsage() {
        System.out.println("Usage: Java Bubble Sort Experiments [options]\n");
        System.out.println("Options:");
        System.out.println("\t-init <length> [path/filename]");
        System.out.println("\t\tInitializes the string data for the experiments.");
        System.out.println("\t\tThe length of the strings is specified by the first argument.");
        System.out.println("\t\tThe filename corresponds to a text file with one string per line, with length at least the specified length.");
        System.out.println("\t\tThe path/filename is optional, and if not specified, the default path and filename is used.");
        System.out.println("\t-seed <seed>");
        System.out.println("\t\tSets the seed for the random number generator.");
        System.out.println("\t-fixed-seed");
        System.out.println("\t\tSets the seed for the random number generator to 17072021.");
        System.out.println("\t-help");
        System.out.println("\t\tPrints this help message.\n");
        System.out.println("(By Erick Garro Elizondo and Cindy Guerrero Toro)");
    }
}
