package com.kjipo.rest.data;

import com.kjipo.data.DataBlock;
import com.kjipo.data.DataProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ParameterEndpoints {
    @Autowired
    private DataProvider dataRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(ParameterEndpoints.class);


    @RequestMapping(value = "/parameters/{logId}")
    public List<String> getParameters(@PathVariable int logId) {
        return dataRepository.getParameters(logId);
    }

    @RequestMapping(value = "/parameters/{logId}/{parameterId}")
    public DataBlock getParameterValueSummary(
            @PathVariable(value = "logId") int logId,
            @PathVariable(value = "parameterId") int parameter,
            @RequestParam(value = "start") long start,
            @RequestParam(value = "stop") long stop) {
        LOGGER.debug("Requesting values for: {}", parameter);
        return dataRepository.getBlockSummary(logId, parameter, start, stop);
    }

    @RequestMapping(value = "/parameters/{logId}/{parameterId}/values")
    public List<Double> getValues(@PathVariable(value = "logId") int logId,
                                  @PathVariable(value = "parameterId") int parameter,
                                  @RequestParam(value = "start") long start,
                                  @RequestParam(value = "stop") long stop) {

        // TODO There should be some caching somewhere

        return dataRepository.getValues(logId, parameter, start, stop);
    }


}
