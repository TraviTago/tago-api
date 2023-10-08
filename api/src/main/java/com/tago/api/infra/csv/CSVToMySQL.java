package com.tago.api.infra.csv;

import com.tago.api.infra.csv.inserter.*;
import com.tago.domain.place.domain.PlaceTag;

public class CSVToMySQL {

    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        //PlaceTagDataInserter.insertPlaceTagData();
        PlaceDataInserter.insertPlaceData();
        //CourseDataInserter.insertCourseData();
        //CoursePlaceDataInserter.insertCoursePlaceData();
        //CourseTagDataInserter.insertCourseTagData();

    }

}
