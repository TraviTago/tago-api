package com.tago.api.infra.csv.inserter;

import com.tago.api.infra.csv.util.DatabaseUtil;
import com.tago.domain.member.domain.vo.Favorite;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class CourseTagDataInserter {

    public static void insertCourseTagData() {

        String insertQuery = "INSERT INTO course_tag (course_id, tag_id) VALUES (?, ?)";

        try (
                Connection connection = DatabaseUtil.getConnection();
                Reader in = new FileReader("/Users/yell/Documents/course_info9.csv");
                PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
        ) {
            Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(in);

            for (CSVRecord record : records) {
                long courseId = Long.parseLong(record.get("Id"));
                String[] tags = record.get("course_tag").split(",");

                for (String tag : tags) {

                    int tagId = Favorite.of(tag.trim()).ordinal();

                    preparedStatement.setLong(1, courseId);
                    preparedStatement.setInt(2, tagId);
                    preparedStatement.addBatch();
                }
            }

            preparedStatement.executeBatch();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

