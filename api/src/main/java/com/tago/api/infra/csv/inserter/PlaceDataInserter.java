package com.tago.api.infra.csv.inserter;


import com.tago.api.infra.csv.util.DatabaseUtil;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class PlaceDataInserter {
    public static void insertPlaceData(){

        String insertQuery = "INSERT INTO place (id, content_id, type_id, title, address, created_time, modified_time, img_url, mapx, mapy, visit, overview, homepage, telephone, rest_date, open_time, parking) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (
                Connection connection = DatabaseUtil.getConnection();
                //Reader in = new FileReader("/Users/yell/Documents/final_place_info.csv");
                Reader in = new FileReader("/Users/yell/Documents/restaurant_info.csv");
                PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
        ) {

            Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(in);
            Long id = 0L;

            connection.setAutoCommit(false);

            try{

                for (CSVRecord record : records) {
                    System.out.println("Place data processing start.");
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

                connection.commit();
                System.out.println("Place data processing finished.");
        } catch (Exception e) {
                connection.rollback();
                e.printStackTrace();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
