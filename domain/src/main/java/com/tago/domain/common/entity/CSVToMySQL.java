package com.tago.domain.common.entity;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.awt.*;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
public class CSVToMySQL {

    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String jdbcUrl = "jdbc:mysql://tago.cjn3xrjw75zo.ap-northeast-2.rds.amazonaws.com:3306/tago?serverTimezone=Asia/Seoul";
        String user = System.getenv("DATABASE_USERNAME");
        String password = System.getenv("DATABASE_PASSWORD");

        String insertQuery = "INSERT INTO place (id, content_id, type_id, title, address, created_time, modified_time, img_url, mapx, mapy, visit, overview, homepage, telephone, rest_date, open_time, parking) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (
                Connection connection = DriverManager.getConnection(jdbcUrl, user, password);
                Reader in = new FileReader("/Users/yell/Documents/place_info.csv");
                PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
        ) {
            Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(in);
            Long id = 0L;

            for (CSVRecord record : records) {
                id++;
                preparedStatement.setLong(1, id);
                preparedStatement.setLong(2, Long.parseLong(record.get("id")));
                preparedStatement.setLong(3, Long.parseLong(record.get("type_id")));
                preparedStatement.setString(4, record.get("title"));
                preparedStatement.setString(5, record.get("address"));
                preparedStatement.setString(6, record.get("created_time"));
                preparedStatement.setString(7, record.get("modified_time"));
                preparedStatement.setString(8, record.get("img_url"));
                preparedStatement.setDouble(9, Double.parseDouble(record.get("mapx")));
                preparedStatement.setDouble(10, Double.parseDouble(record.get("mapy")));
                preparedStatement.setDouble(11, Double.parseDouble(record.get("visit")));
                preparedStatement.setString(12, record.get("overview"));
                preparedStatement.setString(13, record.get("homepage"));
                preparedStatement.setString(14, record.get("telephone"));
                preparedStatement.setString(15, record.get("rest_date"));
                preparedStatement.setString(16, record.get("open_time"));
                preparedStatement.setString(17, record.get("parking"));

                preparedStatement.addBatch();
            }

            preparedStatement.executeBatch();
            System.out.println("Data processing finished.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
