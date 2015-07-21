package com.kjipo.data;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {DataProvider.class})
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:setupSchemaForTest.sql")
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

}
