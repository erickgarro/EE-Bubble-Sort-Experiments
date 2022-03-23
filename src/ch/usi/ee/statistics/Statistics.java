/**
 * Experimentation & Evaluation SP2022
 * USI - Università della Svizzera italiana
 * Project: Java Bubble Sort Experiments
 *
 * Authors: Erick Garro Elizondo & Cindy Guerrero Toro
 */

package ch.usi.ee.statistics;

public class Statistics {
    private double standardDeviation;
    private double standardError;
    private double firstQuartile;
    private double thirdQuartile;
    private double interQuartileRange;
    private double median;
    private double mean;
    private double firstResult;
    private double lastResult;
    private Long[] lastNResults;
    private Long[] lastNResultsSorted;
    private String algorithm;
    private String dataType;
    private String dataOrdering;
    private int arraySize;
    private int iterations;
    private int nLastNumberOfIterations;
    private Long minResult;
    private Long maxResult;

    public Statistics() {
    }

    //Setters
    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public void setDataOrdering(String dataOrdering) {
        this.dataOrdering = dataOrdering;
    }

    public void setArraySize(int arraySize) {
        this.arraySize = arraySize;
    }

    public void setIterations(int iterations) {
        this.iterations = iterations;
    }

    public void setLastNumberOfIterations(int nLastNumberOfIterations) {
        this.nLastNumberOfIterations = nLastNumberOfIterations;
    }

    public void setLastNResults(Long[] lastNResults) {
        this.lastNResults = lastNResults;
    }

    public void setLastNResultsSorted(Long[] lastNResultsSorted) {
        this.lastNResultsSorted = lastNResultsSorted;
    }

    public void setFirstResult(double firstResult) {
        this.firstResult = firstResult;
    }

    public void setLastResult(double lastResult) {
        this.lastResult = lastResult;
    }

    public void setMean(double mean) {
        this.mean = mean;
    }

    public void setMedian(double median) {
        this.median = median;
    }

    public void setFirstQuartile(double firstQuartile) {
        this.firstQuartile = firstQuartile;
    }

    public void setThirdQuartile(double thirdQuartile) {
        this.thirdQuartile = thirdQuartile;
    }

    public void setStandardDeviation(double standardDeviation) {
        this.standardDeviation = standardDeviation;
    }

    public void setMinResult(Long minResult) {
        this.minResult = minResult;
    }

    public void setMaxResult(Long maxResult) {
        this.maxResult = maxResult;
    }

    public void setStandardError(double standardError) {
        this.standardError = standardError;
    }

    public void setInterQuartileRange(double interQuartileRange) {
        this.interQuartileRange = interQuartileRange;
    }

    //Getters
    public String getAlgorithm() {
        return this.algorithm;
    }

    public String getDataType() {
        return this.dataType;
    }

    public String getDataOrdering() {
        return this.dataOrdering;
    }

    public int getArraySize() {
        return this.arraySize;
    }

    public int getIterations() {
        return this.iterations;
    }

    public int getLastNumberOfIterations() {
        return this.nLastNumberOfIterations;
    }

    public Long[] getLastNResults() {
        return this.lastNResults;
    }

    public Long[] getLastNResultsSorted() {
        return this.lastNResultsSorted;
    }

    public double getFirstResult() {
        return this.firstResult;
    }

    public double getLastResult() {
        return this.lastResult;
    }

    public double getMean() {
        return this.mean;
    }

    public double getMedian() {
        return this.median;
    }

    public double getFirstQuartile() {
        return this.firstQuartile;
    }

    public double getThirdQuartile() {
        return this.thirdQuartile;
    }

    public double getStandardDeviation() {
        return this.standardDeviation;
    }

    public double getStandardError() {
        return this.standardError;
    }

    public Long getMinResult() {
        return this.minResult;
    }

    public Long getMaxResult() {
        return this.maxResult;
    }

    public double getInterQuartileRange() {
        return this.interQuartileRange;
    }
}
