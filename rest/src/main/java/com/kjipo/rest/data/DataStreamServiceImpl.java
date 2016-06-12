package com.kjipo.rest.data;


import com.rethinkdb.RethinkDB;
import com.rethinkdb.net.Connection;
import com.rethinkdb.net.Cursor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.concurrent.TimeoutException;


// TODO Figure out what the Component-annotation does

@Component
public class DataStreamServiceImpl implements DataStreamService {
    @Value("${rethinkdb.host}")
    private String host;
    @Value("${rethinkdb.port}")
    private int port;

//    private Connection conn;

    private static final RethinkDB rethinkDb = RethinkDB.r;





    @Bean
    private Connection getConnection() throws TimeoutException {

        System.out.println("Host: " +host);

        return rethinkDb.connection().hostname(host).port(port).connect();
    }


    public Iterable<ParameterUpdate> subscribeToParameterUpdates(String dbName) throws TimeoutException {
        Connection conn = getConnection();
        return () -> {

                System.out.println("Test20");
                Cursor cursor = rethinkDb.table(dbName).changes().run(conn);
                System.out.println("Test21");

            return new Iterator<ParameterUpdate>() {

                @Override
                public boolean hasNext() {
                    return true;
                }

                @Override
                public ParameterUpdate next() {
//                        cursor.next();
                            // TODO Transform update from database to a ParameterUpdate

                    return null;

                }

            };

        };

    }



}
