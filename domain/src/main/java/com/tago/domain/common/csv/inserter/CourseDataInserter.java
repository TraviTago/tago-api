package com.tago.domain.common.csv.inserter;

import com.tago.domain.common.csv.util.DatabaseUtil;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class CourseDataInserter {

    public static void insertCourseData(){
        String insertQuery = "INSERT INTO course (id) VALUES (?)";

        try(
            Connection connection = DatabaseUtil.getConnection();
            Reader in = new FileReader("/Users/yell/Documents/course_info.csv");
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

        ){
            Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(in);

            for (CSVRecord record : records){
                preparedStatement.setLong(1,Long.parseLong(record.get("Id")));
                preparedStatement.addBatch();
            }

            preparedStatement.executeBatch();
            System.out.println("Coursedata finish.");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}

