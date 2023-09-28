package com.tago.api.infra.csv.inserter;


import com.tago.api.infra.csv.util.DatabaseUtil;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class CoursePlaceDataInserter {

    public static void insertCoursePlaceData() {
        String insertQuery = "INSERT INTO course_place (course_id, place_id, `order`) VALUES (?, ?, ?)";

        try (
                Connection connection = DatabaseUtil.getConnection();
                Reader in = new FileReader("/Users/yell/Documents/course_info.csv");
                PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
        ) {
            Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(in);

            for (CSVRecord record : records) {
                long courseId = Long.parseLong(record.get("Id"));
                String[] placeIds = record.get("course_place").split(",");

                int order = 1;
                for (String placeIdStr : placeIds) {
                    long placeId = Long.parseLong(placeIdStr.trim());

                    preparedStatement.setLong(1, courseId);
                    preparedStatement.setLong(2, placeId);
                    preparedStatement.setInt(3, order++);
                    preparedStatement.addBatch();
                }
            }

            preparedStatement.executeBatch();
            System.out.println("Data inserted into course_place.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
