package ch.usi.ee.io;

import ch.usi.ee.experiments.Result;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Stack;

public class Serializer {
    private static String workingDirectory = System.getProperty("user.dir");
    private static String path = workingDirectory + "/data/";
    private static String filename = path + "results.ser";

    /**
     * Serializes the stack of results to a file.
     *
     * @param stack the stack to serialize
     *              serializes the stack into a file
     *              the file is named "serialized_stack.ser"
     *              the file is in the same directory as the program
     *              the file is overwritten if it already exists
     *              the file is created if it does not exist
     *              the file is deleted if it exists and the stack is null
     */
    public static void serializeResults(Stack<Result>[] stack) {
        String workingDirectory = System.getProperty("user.dir");
        String path = workingDirectory + "/results/";
        String filename = path + "results.ser";

        try {
            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(stack);

            oos.close();
            fos.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Results serialized and saved in " + Serializer.getResultsFilePath());
    }

    /**
     * Returns the path to the serialized results file.
     *
     * @return the path and filename where the serialized results are stored.
     */
    public static String getResultsFilePath() {
        return filename;
    }
}