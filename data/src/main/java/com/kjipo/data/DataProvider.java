package com.kjipo.data;

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
    public Double[] getValueSummary(String parameter, long startIndex, long stopIndex) {
        return new Double[0];
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


//        @PostConstruct
//        @SuppressWarnings("unused")
//        public void init() throws SQLException {
//            java.sql.Connection conn = dataSource.getConnection();
//            MetaDataExporter exporter = new MetaDataExporter();
//            exporter.setPackageName("com.kjipo.sqlTables");
//            exporter.setTargetFolder(new File("/home/student/IdeaProjects/ViewerBackend/data/src/main/java"));
//            exporter.export(conn.getMetaData());
//        }


    public static void main(String args[]) {
        SpringApplication app = new SpringApplication(DataProvider.class);
        app.setShowBanner(false);
        app.run(args);
    }


}
