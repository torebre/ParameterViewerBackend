package com.kjipo.rest;

import com.kjipo.data.DataProvider;
import kjipo.com.kjipo.rest.ParameterEndpoints;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {ParameterEndpoints.class, DataProvider.class})
@WebAppConfiguration
public class EndPointTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;


    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getParametersTest() throws Exception {
        mockMvc.perform(get("/parameters"))
                .andDo(mvcResult -> System.out.println("Result: " +mvcResult.getResponse().getContentAsString())
                )
                .andExpect(status().isOk());
    }

    @Test
    public void testRequest() throws Exception {
        // TODO Finish test

        mockMvc.perform(get("/parameters/Parameter1")
                .param("start", "100")
                .param("stop", "110"))
                .andDo(mvcResult -> System.out.println("Result: " +mvcResult.getResponse().getContentAsString())
                )
                .andExpect(status().isOk());
    }

}
