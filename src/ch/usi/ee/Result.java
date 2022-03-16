package ch.usi.ee;

import ch.usi.ee.enums.*;

import java.util.Stack;

public class Result {
    private Algorithm algorithm;
    private DataType dataType;
    private DataOrdering dataOrdering;
    private int arraySize;
    private int iterations;
    private int lastNResults;
    private Stack<Long> elapsedTime;

    public Result() {
        this.algorithm = null;
        this.dataType = null;
        this.dataOrdering = null;
        this.arraySize = 0;
        this.iterations = 0;
        this.lastNResults = 0;
        this.elapsedTime = new Stack<>();
    }

    public Result(int _arraySize, int _iterations, int _lastNResults, Algorithm _algorithmName, DataType _dataType, DataOrdering _dataOrdering) {
        if (_lastNResults > _iterations) {
            throw new IllegalArgumentException("Number of elapsedTime cannot be greater than number of iterations");
        } else {
            this.arraySize = _arraySize;
            this.iterations = _iterations;
            this.lastNResults = _lastNResults;
            this.algorithm = _algorithmName;
            this.dataType = _dataType;
            this.dataOrdering = _dataOrdering;
            elapsedTime = new Stack<>();
        }
    }

    // Getters
    public Algorithm getAlgorithm() {
        return algorithm;
    }

    public DataType getDataType() {
        return dataType;
    }

    public DataOrdering getDataOrdering() {
        return dataOrdering;
    }

    public int getArraySize() {
        return arraySize;
    }

    public int getIterations() {
        return iterations;
    }

    public int getLastNResults() {
        return lastNResults;
    }

    public Stack<Long> getElapsedTime() {
        return elapsedTime;
    }

    // Setters
    public void setAlgorithm(Algorithm _algorithm) {
        algorithm = _algorithm;
    }

    public void setDataType(DataType _dataType) {
        dataType = _dataType;
    }

    public void setDataOrdering(DataOrdering _dataOrdering) {
        dataOrdering = _dataOrdering;
    }

    public void setArraySize(int _arraySize) {
        arraySize = _arraySize;
    }

    public void setIterations(int _iterations) {
        iterations = _iterations;
    }

    public void setLastNResults(int _lastNResults) {
        lastNResults = _lastNResults;
    }

    public void addElapsedTime(long _elapsedTime) {
        elapsedTime.push(_elapsedTime);
    }

    // Get first result of the experiment
    public long getFirstResult() {
        return elapsedTime.get(0);
    }

    // Get elapsed time of the experiment (set during instantiation)
    public long getLastResult() {
        return elapsedTime.get(elapsedTime.size() - 1);
    }

    // Get average elapsed times of the last n results of the experiment
    public double getAverageLastN(int n) {
        double sum = 0;

        for (int i = elapsedTime.size() - lastNResults; i < elapsedTime.size(); i++) {
            sum += elapsedTime.get(i);
        }
        return sum / elapsedTime.size();
    }
}
