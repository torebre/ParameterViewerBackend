package com.kjipo.data;


import java.util.List;


public interface DataRepository {

    DataBlock getBlockSummary(int logId, int parameterId, long startIndex, long stopIndex);

    List<String> getParameters();

    List<Double> getValues(String parameter, long startIndex, long stopIndex);


}
