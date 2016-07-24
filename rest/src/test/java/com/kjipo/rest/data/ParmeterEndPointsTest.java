package com.kjipo.rest.data;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.kjipo.data.DataBlock;
import com.kjipo.data.DataProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.sql.DataSource;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {ParmeterEndPointsTest.Configuration.class,
        ParameterEndpoints.class, DataProvider.class})
@WebIntegrationTest({"server.port=0", "management.port=0"})
public class ParmeterEndPointsTest {
    @Value("${local.server.port}")
    int port;

    private RestTemplate template;

    private static final Logger LOGGER = LoggerFactory.getLogger(ParmeterEndPointsTest.class);


    @Before
    public void setup() {
        template = new TestRestTemplate();
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

        ObjectMapper objectMapper = new ObjectMapper();
        List<Double> values = objectMapper.readValue(response.getBody().getBytes(StandardCharsets.UTF_8), List.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(values.size()).isEqualTo(101);
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
            return new DataBlockHttpMessageConverter();
        }

    }

}