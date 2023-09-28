package com.tago.api.infra.csv;

import com.tago.api.infra.csv.inserter.CourseTagDataInserter;

public class CSVToMySQL {

    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        //PlaceDataInserter.insertPlaceData();
        //CourseDataInserter.insertCourseData();
        //CoursePlaceDataInserter.insertCoursePlaceData();
        CourseTagDataInserter.insertCourseTagData();

    }

}
