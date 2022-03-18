package ch.usi.ee.experiments;

import ch.usi.ee.enums.*;


public class Result {
    private String algorithm;
    private DataType dataType;
    private DataOrdering dataOrdering;
    private int arraySize;
    private int iterations;
    private int lastNResults;
    private long elapsedTime[];

    public Result() {
        this.algorithm = null;
        this.dataType = null;
        this.dataOrdering = null;
        this.arraySize = 0;
        this.iterations = 0;
        this.lastNResults = 0;
    }

    /**
     * @param _arraySize       Size of the array
     * @param _totalIterations Number of iterations
     * @param _lastNResults    Number of last results to consider
     * @param _algorithmName   Name of the algorithm
     * @param _type            Name of the data type
     * @param _ordering        Name of the data ordering
     */
    public Result(DataType _type, DataOrdering _ordering, String _algorithmName, int _arraySize, int _totalIterations, int _lastNResults) {
        if (_lastNResults > _totalIterations) {
            throw new IllegalArgumentException("Number of last N elements cannot be greater than number of iterations");
        } else {
            this.arraySize = _arraySize;
            this.iterations = _totalIterations;
            this.lastNResults = _lastNResults;
            this.algorithm = _algorithmName;
            this.dataType = _type;
            this.dataOrdering = _ordering;
            this.elapsedTime = new long[_totalIterations];
        }
    }

    public Result(DataType _type, DataOrdering _ordering, String _algorithmName, int _arraySize, int _totalIterations) {
        this.dataType = _type;
        this.dataOrdering = _ordering;
        this.algorithm = _algorithmName;
        this.arraySize = _arraySize;
        this.iterations = _totalIterations;
        this.elapsedTime = new long[_totalIterations];
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

    public void addElapsedTime(int index, long _elapsedTime) {
        elapsedTime[index] = _elapsedTime;
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

    /**
     * Get the first result of the experiment
     *
     * @return First result of the experiment
     */
    public long getFirstResult() {
        if (elapsedTime.length == 0) {
            throw new IllegalArgumentException("There are no results");
        }

        return elapsedTime[0];
    }

    /**
     * Get the elapsed time of the experiment (set during instantiation)
     *
     * @return Elapsed time of the experiment
     * @throws IllegalArgumentException if there are no experiments recorded
     */

    public long getLastResult() {
        if (elapsedTime.length == 0) {
            throw new IllegalArgumentException("There are no results");
        }

        return elapsedTime[elapsedTime.length - 1];
    }

    /**
     * Get the average elapsed time of the last n results of the experiment
     *
     * @return Average elapsed time of the last n results of the experiment
     */
    public double getAverageLastN() {
        double sum = 0;

        for (int i = elapsedTime.length - lastNResults; i < elapsedTime.length; i++) {
            sum += elapsedTime[i];
        }

        return sum / lastNResults;
    }
}
