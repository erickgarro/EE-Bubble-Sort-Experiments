package ch.usi.ee.io;

import ch.usi.ee.experiments.Result;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Stack;

public class Deserializer {
    /**
     * Deserializes the stack of results from a file.
     *
     * @return the stack of results
     */
    public static Stack<Result>[] deserializeResults() {
        String workingDirectory = System.getProperty("user.dir");
        String path = workingDirectory + "/results/";
        String filename = path + "results.ser";
        Stack<Result>[] stack = null;

        try {
            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream ois = new ObjectInputStream(fis);

            stack = (Stack<Result>[]) ois.readObject();

            ois.close();
            fis.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return stack;
    }
}
