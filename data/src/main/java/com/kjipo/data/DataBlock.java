package com.kjipo.data;


/**
 * Holds summary information about a range of double values.
 *
 */
public class DataBlock {
    private final double average;
    private final double min;
    private final double max;


    public DataBlock(double average, double min, double max) {
        this.average = average;
        this.min = min;
        this.max = max;
    }

    public double getAverage() {
        return average;
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }
}
