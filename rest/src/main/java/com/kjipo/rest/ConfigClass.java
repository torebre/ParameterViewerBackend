package com.kjipo.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ConfigClass {

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext application = SpringApplication.run(ParameterEndpoints.class, args);
    }


}
