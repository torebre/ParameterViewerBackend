package com.kjipo.data;

import com.mysema.query.sql.codegen.MetaDataExporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.io.File;
import java.sql.SQLException;


/**
 * Just used to easily create the query objects based
 * on the structure of database.
 *
 */
//@SpringBootApplication
//@EnableAutoConfiguration
//@Component
public class CreateQueryObjects {
//    @Autowired
//    private DataSource dataSource;
//
//    @PostConstruct
//    @SuppressWarnings("unused")
//    public void init() throws SQLException {
//        java.sql.Connection conn = dataSource.getConnection();
//        MetaDataExporter exporter = new MetaDataExporter();
//        exporter.setPackageName("com.kjipo.sqlTables");
//        exporter.setTargetFolder(new File("data/src/main/java"));
//        exporter.export(conn.getMetaData());
//    }
//
//
//    public static void main(String args[]) {
//        SpringApplication app = new SpringApplication(CreateQueryObjects.class);
//        app.setShowBanner(false);
//        app.run(args);
//    }

}
