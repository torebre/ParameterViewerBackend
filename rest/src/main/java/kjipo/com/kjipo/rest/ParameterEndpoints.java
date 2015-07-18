package kjipo.com.kjipo.rest;

import com.kjipo.data.DataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ParameterEndpoints {
    private static final Logger LOGGER = LoggerFactory.getLogger(ParameterEndpoints.class);

    @Autowired
    private DataRepository dataRepository;


    @RequestMapping(value = "/parameters/{parameterId}", method = RequestMethod.GET)
    public List<Double> getParameterValues(@PathVariable(value = "parameterId") String parameter,
                                           @RequestParam(value = "start") long start,
                                           @RequestParam(value = "stop") long stop) {
        LOGGER.debug("Requesting values for: {}", parameter);
        return dataRepository.getValues(parameter, start, stop);
    }


    public static void main(String[] args) throws Exception {
        SpringApplication.run(ParameterEndpoints.class, args);

    }

}
