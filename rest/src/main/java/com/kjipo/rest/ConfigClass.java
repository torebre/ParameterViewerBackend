package com.kjipo.rest;

import com.kjipo.rest.data.ParameterEndpoints;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication
@EnableCaching
public class ConfigClass {


    public static void main(String[] args) throws Exception {
        SpringApplication.run(new Object[] {ParameterEndpoints.class, CacheTest.class}, args);
    }


}
