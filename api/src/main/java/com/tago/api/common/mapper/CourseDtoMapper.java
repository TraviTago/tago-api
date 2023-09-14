package com.tago.api.common.mapper;

import com.tago.api.common.exception.MainPlaceNotFoundException;
import com.tago.api.course.dto.response.CourseRecommendResponse;
import com.tago.domain.course.domain.Course;
import com.tago.domain.course.domain.CoursePlace;
import com.tago.domain.place.exception.PlaceNotFoundException;

import java.util.List;

public class CourseDtoMapper {

    private static int TOTAL_TIME = 480;

    public static CourseRecommendResponse toDto(Course course, Long placeId) {
        List<CoursePlace> coursePlaces = course.getCoursePlaces();

        return new CourseRecommendResponse(
                course.getId(),
                getMainPlaceImgUrl(coursePlaces, placeId),
                TOTAL_TIME,
                getPlacesTitle(coursePlaces)
        );
    }

    private static String getMainPlaceImgUrl(List<CoursePlace> coursePlaces, Long placeId) {
        return coursePlaces.stream()
                .filter(coursePlace -> coursePlace.getPlaceId().equals(placeId))
                .map(CoursePlace::getPlaceImgUrl)
                .findFirst()
                .orElseThrow(MainPlaceNotFoundException::new);
    }

    private static List<String> getPlacesTitle(List<CoursePlace> coursePlaces) {
        return coursePlaces.stream()
                .map(CoursePlace::getPlaceTitle)
                .toList();
    }
}
