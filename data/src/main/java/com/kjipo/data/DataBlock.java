package com.kjipo.data;


/**
 * Holds summary information about a range of double values.
 *
 */
public class DataBlock {
    private double average;
    private double min;
    private double max;


    public DataBlock() {

    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DataBlock dataBlock = (DataBlock) o;

        if (Double.compare(dataBlock.average, average) != 0) return false;
        if (Double.compare(dataBlock.min, min) != 0) return false;
        return Double.compare(dataBlock.max, max) == 0;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(average);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(min);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(max);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "DataBlock{" +
                "average=" + average +
                ", min=" + min +
                ", max=" + max +
                '}';
    }

}
