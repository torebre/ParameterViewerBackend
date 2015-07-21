package com.kjipo.rest;

import com.kjipo.rest.data.ParameterEndpoints;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConfigClass {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ParameterEndpoints.class, args);
    }


}
