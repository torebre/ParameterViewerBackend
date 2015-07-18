package com.kjipo.data;


import java.util.List;

public interface DataRepository {


    Double[] getValueSummary(String parameter, long startIndex, long stopIndex);

    List<String> getParameters();

    List<Double> getValues(String parameter, long startIndex, long stopIndex);






}
