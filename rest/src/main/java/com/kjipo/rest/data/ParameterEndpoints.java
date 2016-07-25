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

    // TODO Check that this assumption is correct:
    // The ResponseBody-annotation tells Spring that the
    // returned value does not need to go through a rendering
    // layer on the server

    @RequestMapping(value = "/parameters/{logId}")
    public @ResponseBody List<String> getParameters(@PathVariable int logId) {
        return dataRepository.getParameters(logId);
    }

    @RequestMapping(value = "/parameters/{logId}/{parameterId}")
    public @ResponseBody DataBlock getParameterValueSummary(
            @PathVariable(value = "logId") int logId,
            @PathVariable(value = "parameterId") int parameter,
            @RequestParam(value = "start") long start,
            @RequestParam(value = "stop") long stop) {
        LOGGER.debug("Requesting values for: {}", parameter);
        return dataRepository.getBlockSummary(logId, parameter, start, stop);
    }

    @RequestMapping(value = "/parameters/{logId}/{parameterId}/values")
    public @ResponseBody List<Double> getValues(@PathVariable(value = "logId") int logId,
                                  @PathVariable(value = "parameterId") int parameter,
                                  @RequestParam(value = "start") long start,
                                  @RequestParam(value = "stop") long stop) {

        // TODO There should be some caching somewhere

        LOGGER.info("Requesting values: {}, {}, {}, {}", logId, parameter, start, stop);

        return dataRepository.getValues(logId, parameter, start, stop);
    }


}
