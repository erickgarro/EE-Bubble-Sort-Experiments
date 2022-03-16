package ch.usi.ee;

import ch.usi.ee.enums.*;

public class Result {
    private int arraySize;
    private int iterations;
    private int lastNumberOfResults;
    private Algorithm algorithmName;
    private DataType dataType;
    private DataOrdering dataOrdering;
    private int[] results;

    public Result(int _arraySize, int _iterations, int _lastNumberOfResults, Algorithm _algorithmName, DataType _dataType, DataOrdering _dataOrdering) {
        if (_lastNumberOfResults -1  > _iterations) {
            throw new IllegalArgumentException("Number of results cannot be greater than number of iterations");
        } else {

            this.arraySize = _arraySize;
            this.iterations = _iterations;
            this.lastNumberOfResults = _lastNumberOfResults;
            this.algorithmName = _algorithmName;
            this.dataType = _dataType;
            this.dataOrdering = _dataOrdering;
            results = new int[_iterations];
        }
    }

    // Get first result of the experiment
    public int getFirstResult() {
        return results[0];
    }

    // Get average of the last number of results of the experiment (set during instantiation)
    public double getAverage() {
        double sum = 0;
        for (int i = results.length - lastNumberOfResults - 1; i < results.length; i++) {
            sum += results[i];
        }
        return sum / results.length;
    }

    // Get average of the last n results of the experiment
    public double getAverage(int n) {
        double sum = 0;

        if (results.length - 1 < n) {

            for (int i = results.length - n - 1; i < results.length; i++) {
                sum += results[i];
            }
            return sum / results.length;
        }
        return getAverage();
    }
}
