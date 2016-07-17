package com.kjipo.data;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {DataProvider.class, DataRepository.class, DataRetrievalTest.Configuration.class})
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:setupSchemaIncludeEventsTable.sql")
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:addDataForTest.sql")
public class DataRetrievalTest {
        @Autowired
        private DataRepository dataProvider;


        @Test
        public void getDataBlockTest() {
            DataBlock dataBlock = dataProvider.getBlockSummary(1, 1, 0, 100);
            System.out.println("Data block: " +dataBlock);

            assertThat(dataBlock.getMin(), lessThan(0D));
            assertThat(dataBlock.getMax(), greaterThan(0D));
        }


        @org.springframework.context.annotation.Configuration
        public static class Configuration {
            @Bean
            @ConfigurationProperties(prefix = "datasource.mine")
            public DataSource dataSource() {
                return DataSourceBuilder.create().url("jdbc:h2:mem:mydb").build();
            }

        }

}
