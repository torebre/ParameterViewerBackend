package com.kjipo.rest.data;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.kjipo.data.DataBlock;
import com.kjipo.data.DataProvider;
import com.kjipo.data.IndexRange;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.criteria.CriteriaBuilder;
import javax.sql.DataSource;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {ParameterEndPointsTest.Configuration.class,
        ParameterEndpoints.class, DataProvider.class},
webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
//@WebIntegrationTest({"server.port=9800", "management.port=0"})
public class ParameterEndPointsTest {
    @Value("${local.server.port}")
    int port;

    private RestTemplate template;

    private static final Logger LOGGER = LoggerFactory.getLogger(ParameterEndPointsTest.class);


    @Before
    public void setup() {
        template = new RestTemplate();
    }

    @Test
    public void retrieveDataRange() {
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl("http://127.0.0.1:" +port +"/parameters/1/1")
                .queryParam("start", 0)
                .queryParam("stop", 100);

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.parseMediaType("application/x-protobuf")));
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        template.getMessageConverters().add(new DataBlockHttpMessageConverter());
        ResponseEntity<DataBlock> response =
                template.exchange(builder.toUriString(), HttpMethod.GET, entity, DataBlock.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
    }

    @Test
    public void retrieveDataRangeJson() throws IOException {
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl("http://127.0.0.1:" +port +"/parameters/1/1")
                .queryParam("start", 0)
                .queryParam("stop", 100);

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON_UTF8));
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        ResponseEntity<String> response = template.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        DataBlock dataBlock = objectMapper.readValue(response.getBody().getBytes(StandardCharsets.UTF_8), DataBlock.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(dataBlock).isNotNull();
    }

    @Test
    public void getValuesTest() throws IOException {
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl("http://127.0.0.1:" +port +"/parameters/1/1/values")
                .queryParam("start", 0)
                .queryParam("stop", 100);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON_UTF8));
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        ResponseEntity<String> response = template.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);

        try {
            Thread.sleep(Long.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ObjectMapper objectMapper = new ObjectMapper();
        List<Double> values = objectMapper.readValue(response.getBody().getBytes(StandardCharsets.UTF_8), List.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(values.size()).isEqualTo(101);
    }

    @Test
    public void getValueSummaryTest() throws IOException {
        List<Long> indexRanges = Lists.newArrayList(0L, 20L, 25L, 35L);
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl("http://127.0.0.1:" +port +"/parameters/1/1/valueSummary")
                .queryParam("ranges", indexRanges.toArray());
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON_UTF8));
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        LOGGER.info("Request URI: {}", builder.toUriString());



        ResponseEntity<String> response = template.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);

        LOGGER.info("Response: {}", response.getBody());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);


//        ObjectMapper objectMapper = new ObjectMapper();
//        List<Double> values = objectMapper.readValue(response.getBody().getBytes(StandardCharsets.UTF_8), List.class);
//
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(values.size()).isEqualTo(101);

    }

    @Test
    public void getValueIndexRangeTest() throws IOException {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://127.0.0.1:" + port + "/indexRange/1");
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON_UTF8));
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        ResponseEntity<IndexRange> response = template.exchange(builder.toUriString(), HttpMethod.GET, entity, IndexRange.class);

        LOGGER.info("Response: {}", response.getBody());

        // TODO Remove sleep
        try {
            Thread.sleep(Long.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        IndexRange responseBody = response.getBody();
        assertThat(responseBody.getStart()).isEqualTo(0);
        assertThat(responseBody.getStop()).isEqualTo(999);
    }

        // This class has a method that returns a DataSource. It will
    // cause a datasource to be available for injection in the
    // DataProvider-class
    @org.springframework.context.annotation.Configuration
    // Need the @EnableAutoConfiguration annotation for a web server
    // to be set up
    @EnableAutoConfiguration
    public static class Configuration {
        @Value("${jdbcUrl}")
        String jdbcUrl;

        @Bean
        public DataSource dataSource() {
            return DataSourceBuilder.create().url(jdbcUrl).build();
        }

        @Bean
        public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
            // Need this method for the fields annotated with @Value to be resolved
            return new PropertySourcesPlaceholderConfigurer();
        }

        @Bean
        DataBlockHttpMessageConverter dataBlockHttpMessageConverter() {
            // The presence of this will enable REST-methods that
            // return a DataBlock-object to be able to return it
            // as Protobuf encoded data
            return new DataBlockHttpMessageConverter();
        }

        @Bean
        public WebMvcConfigurer corsConfigurer() {
            // This is to avoid problems with CORS when doing tests
            return new WebMvcConfigurerAdapter() {
                @Override
                public void addCorsMappings(CorsRegistry registry) {
                    registry.addMapping("/**").allowedOrigins("*");
                }
            };

        }

    }

}