package com.kjipo.data;

import com.kjipo.sqlTables.QParameters;
import com.kjipo.sqlTables.QTest;
import com.mysema.query.sql.HSQLDBTemplates;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.sql.SQLTemplates;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Component
public class DataProvider implements DataRepository {
    private final DataSource dataSource;

    private static final Logger LOGGER = LoggerFactory.getLogger(DataProvider.class);


    @Inject
    public DataProvider(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public DataBlock getBlockSummary(int logId, int parameterId, long startIndex, long stopIndex) {
        QTest testData = new QTest("c");
        SQLTemplates dialect = new HSQLDBTemplates();

        // TODO Only at test stage now, logId and parameterId not used

        try (
                Connection connection = dataSource.getConnection()
        ) {
            SQLQuery query = new SQLQuery(connection, dialect);
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
    public List<String> getParameters(int logId) {
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
    public List<Double> getValues(int logId, int parameterId, long startIndex, long stopIndex) {
        QTest testData = new QTest("c");
        SQLTemplates dialect = new HSQLDBTemplates();
        try (
                Connection connection = dataSource.getConnection()
        ) {
            SQLQuery query = new SQLQuery(connection, dialect);
            return query.from(testData)
                    .where(testData.idtime.between(startIndex, stopIndex))
                    .list(testData.value1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<DataBlock> getValues(int logId, int parameterId, RangeTuple... rangeTuples) {
        List<DataBlock> result = new ArrayList<>(rangeTuples.length);
        for (RangeTuple rangeTuple : rangeTuples) {
            result.add(getBlockSummary(logId, parameterId, rangeTuple.getStart(), rangeTuple.getStop()));
        }
        return result;
    }

    @Override
    public IndexRange getIndexRange(int logId) {
        QTest testData = new QTest("c");
        SQLTemplates dialect = new HSQLDBTemplates();
        try (
                Connection connection = dataSource.getConnection()
        ) {
            SQLQuery query = new SQLQuery(connection, dialect);
            Integer minIndex = query.from(testData)
                    .singleResult(testData.idtime.min());
            query = new SQLQuery(connection, dialect);
            Integer maxIndex = query.from(testData)
                    .singleResult(testData.idtime.max());
            return new IndexRange(minIndex, maxIndex);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
