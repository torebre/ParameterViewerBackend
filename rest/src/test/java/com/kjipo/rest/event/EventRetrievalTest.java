package com.kjipo.rest.event;


import com.kjipo.event.Config;
import com.kjipo.event.EventRepository;
import com.kjipo.event.Events;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import javax.sql.DataSource;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {EventEndpoints.class, Events.class, EventRepository.class}) //, EventRetrievalTest.Config.class})
@AutoConfigureMockMvc
@DataJpaTest
public class EventRetrievalTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() throws Exception {
//        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getParametersTest() throws Exception {
//        restTemplate.getForEntity("/events/1", );

        mockMvc.perform(get("/events/1"))
                .andDo(mvcResult -> System.out.println("Result: " + mvcResult.getResponse().getContentAsString())
                )
                .andExpect(status().isOk());
    }


    @EnableAutoConfiguration
    @EnableJpaRepositories
    static class Config {

            @Autowired
            private DataSource dataSource;


        }

}



