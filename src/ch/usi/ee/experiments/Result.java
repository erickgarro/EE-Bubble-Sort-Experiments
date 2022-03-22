package ch.usi.ee.experiments;

import ch.usi.ee.enums.*;


public class Result {
    private String algorithmName;
    private Algorithm algorithm;
    private DataType dataType;
    private DataOrdering dataOrdering;
    private int arraySize;
    private int iterations;
    private long elapsedTime[];

    public Result(DataType _type, DataOrdering _ordering, String _algorithmName, int _arraySize, int _totalIterations) {
        this.dataType = _type;
        this.dataOrdering = _ordering;
        this.algorithmName = _algorithmName;
        this.arraySize = _arraySize;
        this.iterations = _totalIterations;
        this.elapsedTime = new long[_totalIterations];
    }

    // Setters
    public void setAlgorithmName(String _algorithm) {
        algorithmName = _algorithm;
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

    public void setAlgorithm(Algorithm _algorithm){
        algorithm = _algorithm;
    }

    public void addElapsedTime(int index, long _elapsedTime) {
        elapsedTime[index] = _elapsedTime;
    }

    // Getters
    public Algorithm getAlgorithm() {
        return algorithm;
    }

    public String getAlgorithmName() {
        return algorithmName;
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
     * Get the last n results of the experiment
     *
     * @return Average elapsed time of the last n results of the experiment
     */
    public Long[] getLastNResults(int n) throws IllegalArgumentException {
        if (elapsedTime.length < n) {
            throw new IllegalArgumentException("There are not enough results");
        }

        Long[] lastNResults = new Long[n];

        for (int i = 0; i < n; i++) {
            lastNResults[i] = elapsedTime[elapsedTime.length - n + i];
        }

        return lastNResults;
    }

    /**
     * Get the total number of iterations ran for the experiment
     * @return Total number of iterations
     */
    public int getNumberOfIterations() {
        return iterations;
    }
}
