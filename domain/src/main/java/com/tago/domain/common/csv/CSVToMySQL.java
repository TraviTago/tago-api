package com.tago.domain.common.csv;

import com.tago.domain.common.csv.inserter.CourseDataInserter;
import com.tago.domain.common.csv.inserter.PlaceDataInserter;

public class CSVToMySQL {

    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        PlaceDataInserter.insertPlaceData();
        CourseDataInserter.insertCourseData();

    }

}
