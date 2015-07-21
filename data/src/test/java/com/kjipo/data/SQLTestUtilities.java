package com.kjipo.data;


import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * Contains helper methods for creating test data for the database.
 */
public final class SQLTestUtilities {


    private SQLTestUtilities() {

    }


    @SuppressWarnings("unused")
    private static void createTestData() throws IOException {
        final DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.systemDefault());
        File file = new File("/data/src/main/resources/addDataForTest.sql");
        Files.deleteIfExists(file.toPath());
        int counter = 0;

        try (
                final BufferedWriter bufferedWriter = Files.newBufferedWriter(file.toPath(), StandardCharsets.UTF_8, StandardOpenOption.CREATE_NEW)
        ) {
            bufferedWriter.write("insert into Test (idTime,value1) values ");
            List<String> rows = Stream.generate(new Supplier<String>() {
                final Instant startIndex = Instant.ofEpochSecond(0);
                long currentTimeStep = 0;
                double sineStep = 0;

                @Override
                public String get() {
                    return "('" + formatter.format(startIndex.plus(currentTimeStep++, ChronoUnit.SECONDS)) + "','" + Double.valueOf(Math.sin(sineStep += 0.1)).toString() + "')";
                }
            })
                    .limit(1000)
                    .collect(Collectors.toList());

            for (String s : rows) {
                bufferedWriter.write(s);
                if (counter != 999) {
                    bufferedWriter.write(",");
                }
                ++counter;
            }

        }
    }


    private static void createTestDataWitLongIndex() throws IOException {
        File file = new File("/rest/src/test/resources/addDataForTest.sql");
        Files.deleteIfExists(file.toPath());
        int counter = 0;

        try (
                final BufferedWriter bufferedWriter = Files.newBufferedWriter(file.toPath(), StandardCharsets.UTF_8, StandardOpenOption.CREATE_NEW)
        ) {
            bufferedWriter.write("insert into Test (idTime,value1) values ");
            List<String> rows = Stream.generate(new Supplier<String>() {
                long currentTimeStep = 0;
                double sineStep = 0;

                @Override
                public String get() {
                    return "('" + currentTimeStep++ + "','" + Double.valueOf(Math.sin(sineStep += 0.1)).toString() + "')";
                }
            })
                    .limit(1000)
                    .collect(Collectors.toList());

            for (String s : rows) {
                bufferedWriter.write(s);
                if (counter != 999) {
                    bufferedWriter.write(",");
                }
                ++counter;
            }

        }
    }


    public static void main(String args[]) throws Exception {
//        createTestData();
        createTestDataWitLongIndex();

    }

}
