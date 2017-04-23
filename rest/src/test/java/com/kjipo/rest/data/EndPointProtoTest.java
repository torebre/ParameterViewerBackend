package com.kjipo.rest.data;


import com.kjipo.data.DataProvider;
import com.kjipo.proto.MessagesProto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {EndPointProtoTest.RestClientConfiguration.class, ParameterEndpoints.class, DataProvider.class})
@WebAppConfiguration
public class EndPointProtoTest {

    @Configuration
    public static class RestClientConfiguration {

        @Bean
        RestTemplate restTemplate(ProtobufHttpMessageConverter hmc) {
            return new RestTemplate(Collections.singletonList(hmc));
        }

        @Bean
        ProtobufHttpMessageConverter protobufHttpMessageConverter() {
            return new ProtobufHttpMessageConverter();
        }
    }

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void getDataBlockTest() {
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl("http://127.0.0.1:9000/parameters/1/1")
                .queryParam("start", 0)
                .queryParam("stop", 100);

        ResponseEntity<MessagesProto.DoubleDataBlock> dataBlock = restTemplate.getForEntity(builder.build().encode().toUri(), MessagesProto.DoubleDataBlock.class);

        assertThat(dataBlock.getStatusCode(), is(HttpStatus.OK));
    }


}
