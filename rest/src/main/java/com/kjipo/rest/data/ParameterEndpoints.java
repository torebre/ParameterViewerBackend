package com.kjipo.rest.data;

import com.kjipo.data.DataBlock;
import com.kjipo.data.DataProvider;
import com.kjipo.data.RangeTuple;
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

    // TODO Check that this assumption is correct:
    // The ResponseBody-annotation tells Spring that the
    // returned value does not need to go through a rendering
    // layer on the server

    @RequestMapping(value = "/parameters/{logId}")
    public
    @ResponseBody
    List<String> getParameters(@PathVariable int logId) {
        return dataRepository.getParameters(logId);
    }

    @RequestMapping(value = "/parameters/{logId}/{parameterId}")
    public
    @ResponseBody
    DataBlock getParameterValueSummary(
            @PathVariable(value = "logId") int logId,
            @PathVariable(value = "parameterId") int parameter,
            @RequestParam(value = "start") long start,
            @RequestParam(value = "stop") long stop) {
        LOGGER.debug("Requesting values for: {}", parameter);
        return dataRepository.getBlockSummary(logId, parameter, start, stop);
    }

    @RequestMapping(value = "/parameters/{logId}/{parameterId}/values")
    public
    @ResponseBody
    List<Double> getValues(@PathVariable(value = "logId") int logId,
                           @PathVariable(value = "parameterId") int parameter,
                           @RequestParam(value = "start") long start,
                           @RequestParam(value = "stop") long stop) {

        // TODO There should be some caching somewhere

        LOGGER.info("Requesting values: {}, {}, {}, {}", logId, parameter, start, stop);

        return dataRepository.getValues(logId, parameter, start, stop);
    }


    @RequestMapping(value = "/parameters/{logId}/{parameterId}/valueSummary")
    public
    @ResponseBody
    List<DataBlock> getBlockSummaries(
            @PathVariable int logId,
            @PathVariable int parameterId,
            @RequestParam List<Long> ranges) {

        // TODO Add better error handling

        LOGGER.info("Processing request: {}, {}, {}", logId, parameterId, ranges);

        RangeTuple rangeTuple[] = new RangeTuple[ranges.size() / 2];
        int counter = 0;
        Long temp = null;
        for(Long value : ranges) {
            if(temp != null) {
                rangeTuple[counter++] = new RangeTuple(temp, value);
                temp = null;
            }
            else {
                temp = value;
            }
        }

        LOGGER.info("Test20");

        return dataRepository.getValues(logId, parameterId, rangeTuple);
    }


}
