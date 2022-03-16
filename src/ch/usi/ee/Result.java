package ch.usi.ee;

import ch.usi.ee.enums.*;

import java.util.Stack;

public class Result {
    private String algorithm;
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

    /**
     * @param _arraySize     Size of the array
     * @param _iterations    Number of iterations
     * @param _lastNResults  Number of last results to consider
     * @param _algorithmName Name of the algorithm
     * @param _dataType      Name of the data type
     * @param _dataOrdering  Name of the data ordering
     */
    public Result(int _arraySize, int _iterations, int _lastNResults, String _algorithmName, DataType _dataType, DataOrdering _dataOrdering) {
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
    public Result(DataType _type, DataOrdering _ordering, String _algorithmName, int arraySize, int totalIterations) {
        this.dataType = _type;
        this.dataOrdering = _ordering;
        this.algorithm = _algorithmName;
        this.arraySize = arraySize;
        this.iterations = totalIterations;
        this.elapsedTime = new Stack<>();
    }

    // Setters
    public void setAlgorithm(String _algorithm) {
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

    // Getters
    public String getAlgorithm() {
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

    /**
     * Get the first result of the experiment
     * @return First result of the experiment
     */
    public long getFirstResult() {
        if (elapsedTime.size() == 0) {
            throw new IllegalArgumentException("There are no results");
        }

        return elapsedTime.get(0);
    }

    /**
     * Get the elapsed time of the experiment (set during instantiation)
     *
     * @return Elapsed time of the experiment
     * @throws IllegalArgumentException if there are no experiments recorded
     */

    public long getLastResult() {
        if (elapsedTime.size() == 0) {
            throw new IllegalArgumentException("There are no results");
        }

        return elapsedTime.get(elapsedTime.size() - 1);
    }

    /**
     * Get the average elapsed time of the last n results of the experiment
     * @param n Number of last results to consider
     * @return Average elapsed time of the last n results of the experiment
     */
    public double getAverageLastN(int n) {
        double sum = 0;

        for (int i = elapsedTime.size() - lastNResults; i < elapsedTime.size(); i++) {
            sum += elapsedTime.get(i);
        }

        return sum / elapsedTime.size();
    }
}
