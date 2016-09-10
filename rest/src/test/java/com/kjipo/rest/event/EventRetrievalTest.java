package com.kjipo.rest.event;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.kjipo.event.Events;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * This test will start the full application.
 */
@RunWith(SpringRunner.class)
// Need to specify webEnvironment for @SpringBootTest for TestRestTemplate to be injected
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EventRetrievalTest {
    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void getParametersTest() throws Exception {
        ResponseEntity<String> response = restTemplate.getForEntity("/events/1", String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        List<Events> events = objectMapper.readValue(response.getBody(),
                TypeFactory.defaultInstance().constructCollectionType(List.class, Events.class));
        assertThat(events).containsOnly(new Events(1L, 1, 0, 2), new Events(2L, 1, 1, 5));
    }


}



