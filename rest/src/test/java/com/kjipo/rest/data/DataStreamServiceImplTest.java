package com.kjipo.rest.data;


import com.kjipo.data.DataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Properties;
import java.util.concurrent.TimeoutException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DataStreamServiceImplTest.TestConfiguration.class)
@SpringApplicationConfiguration(classes = {DataStreamServiceImpl.class})
//@ComponentScan(DataStreamServiceImpl.class)
public class DataStreamServiceImplTest {

    @Autowired
    DataStreamService dataStreamService;

//    @Bean
//    public DataStreamService getDataStreamService() {
//        return new DataStreamServiceImpl();
//    }



    static class TestConfiguration {
        @Bean
        public PropertySourcesPlaceholderConfigurer properties() throws Exception {
            final PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
            Properties properties = new Properties();

            System.out.println("Test20");

            properties.setProperty("rethinkdb.host", "localhost");

            pspc.setProperties(properties);
            return pspc;
        }
    }


    @Test
    public void streamValues() throws TimeoutException {
//        DataStreamServiceImpl dataStreamService = getService();

        dataStreamService.subscribeToParameterUpdates("Test");

    }


//    @Bean
//    private DataStreamServiceImpl getService() throws TimeoutException {
//        return new DataStreamServiceImpl();
//
//    }


}
