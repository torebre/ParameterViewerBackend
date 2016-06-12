package com.kjipo.rest.data;


import com.rethinkdb.RethinkDB;
import com.rethinkdb.net.Connection;
import com.rethinkdb.net.Cursor;

import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeoutException;
import java.util.stream.IntStream;


public interface DataStreamService {


    Iterable<ParameterUpdate> subscribeToParameterUpdates(String dbName) throws TimeoutException;




}
