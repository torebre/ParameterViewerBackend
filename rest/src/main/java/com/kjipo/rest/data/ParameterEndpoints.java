package com.kjipo.rest.data;

import com.kjipo.data.DataProvider;
import com.kjipo.data.DataBlock;
import com.kjipo.proto.MessagesProto;
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
    @Autowired
    private DataProvider dataRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(ParameterEndpoints.class);


    @RequestMapping(value = "/parameters/{logId}")
    public List<String> getParameters() {
        return dataRepository.getParameters();
    }


    @RequestMapping(value = "/parameters/{logId}/{parameterId}")
    public MessagesProto.DoubleDataBlock getParameterValues(
            @PathVariable(value = "logId") int logId,
            @PathVariable(value = "parameterId") int parameter,
            @RequestParam(value = "start") long start,
            @RequestParam(value = "stop") long stop) {
        LOGGER.debug("Requesting values for: {}", parameter);
        DataBlock dataBlock = dataRepository.getBlockSummary(logId, parameter, start, stop);
        return MessagesProto.DoubleDataBlock.newBuilder()
                .setAverage(dataBlock.getAverage())
                .setMinimum(dataBlock.getMin())
                .setMaximum(dataBlock.getMax())
                .build();
    }

}
