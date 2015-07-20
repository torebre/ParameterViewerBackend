package com.kjipo.rest;

import com.kjipo.data.DataProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@ComponentScan("com.kjipo.data")
@EnableAutoConfiguration
@RestController
public class ParameterEndpoints {
    private static final Logger LOGGER = LoggerFactory.getLogger(ParameterEndpoints.class);

    @Autowired
    DataProvider dataRepository;


    @RequestMapping(value = "/parameters")
    public List<String> getParameters() {
        return dataRepository.getParameters();
    }


    @RequestMapping(value = "/parameters/{parameterId}")
    public List<Double> getParameterValues(@PathVariable(value = "parameterId") String parameter,
                                           @RequestParam(value = "start") long start,
                                           @RequestParam(value = "stop") long stop) {
        LOGGER.debug("Requesting values for: {}", parameter);
        return dataRepository.getValues(parameter, start, stop);
    }



}
