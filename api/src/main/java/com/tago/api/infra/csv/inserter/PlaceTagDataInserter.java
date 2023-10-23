package com.tago.api.infra.csv.inserter;

import com.tago.api.infra.csv.util.DatabaseUtil;
import com.tago.domain.member.domain.vo.Favorite;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

public class PlaceTagDataInserter {
    public static void insertPlaceTagData(){
        String insertQuery = "INSERT INTO place_tag (place_id, tag_id) VALUES (?, ?)";

        try (

                Connection connection = DatabaseUtil.getConnection();
                Reader in = new FileReader("/Users/yell/Documents/place_v2.csv");
                PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
        ){
            Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(in);

            for (CSVRecord record : records) {
                long placeId = Long.parseLong(record.get("id"));
                String[] tags = record.get("추천태그").split(",");

                System.out.println(placeId);

                for (String tag : tags) {

                    int tagId = Favorite.of(tag.trim()).ordinal()+1;

                    preparedStatement.setLong(1, placeId);
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



