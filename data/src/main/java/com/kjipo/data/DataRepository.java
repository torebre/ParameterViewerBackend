package com.kjipo.data;


import java.util.List;


public interface DataRepository {

    DataBlock getBlockSummary(int logId, int parameterId, long startIndex, long stopIndex);

    List<String> getParameters(int logId);

    List<Double> getValues(int logId, int parameterId, long startIndex, long stopIndex);

    List<DataBlock> getValues(int logId, int parameterId, RangeTuple... rangeTuples);


}
