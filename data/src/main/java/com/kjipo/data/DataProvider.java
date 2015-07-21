package com.kjipo.data;

import com.kjipo.sqlTables.QParameters;
import com.kjipo.sqlTables.QTest;
import com.mysema.query.sql.HSQLDBTemplates;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.sql.SQLTemplates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;


@EnableAutoConfiguration
@Component
public class DataProvider implements DataRepository {
    @Autowired
    private DataSource dataSource;


    @Override
    public DataBlock getBlockSummary(int logId, int parameterId, long startIndex, long stopIndex) {
        QTest testData = new QTest("c");
        SQLTemplates dialect = new HSQLDBTemplates();

        // TODO Only at test stage now, logId andd parameterId not used

        try {
            SQLQuery query = new SQLQuery(dataSource.getConnection(), dialect);
            List<Double> values = query.from(testData)
                    .where(testData.idtime.between(startIndex, stopIndex))
                    .list(testData.value1);

            // TODO This is not efficient
            return new DataBlock(
            values.stream().mapToDouble(Double::doubleValue).average().getAsDouble(),
                    values.stream().mapToDouble(Double::doubleValue).min().getAsDouble(),
                    values.stream().mapToDouble(Double::doubleValue).max().getAsDouble());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<String> getParameters() {
        QParameters parameters = new QParameters("c");
        SQLTemplates dialect = new HSQLDBTemplates();
        try {
            SQLQuery query = new SQLQuery(dataSource.getConnection(), dialect);
            return query.from(parameters).list(parameters.name);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Double> getValues(String parameter, long startIndex, long stopIndex) {
        QTest testData = new QTest("c");
        SQLTemplates dialect = new HSQLDBTemplates();
        try {
            SQLQuery query = new SQLQuery(dataSource.getConnection(), dialect);
            return query.from(testData)
                    .where(testData.idtime.between(startIndex, stopIndex))
                    .list(testData.value1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String args[]) {
        SpringApplication app = new SpringApplication(DataProvider.class);
        app.setShowBanner(false);
        app.run(args);
    }


}
