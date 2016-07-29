package com.kjipo.data



interface DataProvider2 {

    fun getBlockSummary(logId: Int, parameterId: Int, startIndex: Long, stopIndex: Long): DataBlock

    fun getParameters(): List<String>

    fun getValues(parameter: String, startIndex: Long, stopIndex: Long): List<Double>

}