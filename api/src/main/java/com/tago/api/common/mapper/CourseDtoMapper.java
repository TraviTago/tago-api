package com.tago.api.common.mapper;

import com.tago.api.common.exception.MainPlaceNotFoundException;
import com.tago.api.course.dto.response.CourseRecommendResponse;
import com.tago.api.course.dto.response.CourseRecommendResponse.PlacePreview;
import com.tago.domain.course.domain.Course;
import com.tago.domain.course.domain.CoursePlace;
import com.tago.domain.place.domain.Place;
import com.tago.domain.place.exception.PlaceNotFoundException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CourseDtoMapper {

    private static int TOTAL_TIME = 480;

    public static CourseRecommendResponse toDto(Course course, Long placeId) {
        List<CoursePlace> coursePlaces = course.getCoursePlaces();

        return new CourseRecommendResponse(
                getMainPlaceImgUrl(coursePlaces, placeId),
                TOTAL_TIME,
                getPlaces(coursePlaces)
        );
    }

    private static String getMainPlaceImgUrl(List<CoursePlace> coursePlaces, Long placeId) {
        if (placeId < 0) {
            return coursePlaces.get(0).getPlaceImgUrl();
        }

        return coursePlaces.stream()
                .filter(coursePlace -> coursePlace.getPlaceId().equals(placeId))
                .map(CoursePlace::getPlaceImgUrl)
                .findFirst()
                .orElseThrow(MainPlaceNotFoundException::new);
    }

private static List<PlacePreview> getPlaces(List<CoursePlace> coursePlaces) {
    Set<Place> uniquePlaces = new HashSet<>();


    List<PlacePreview> uniquePlacePreviews = coursePlaces.stream()
            .map(coursePlace -> {
                Place place = coursePlace.getPlace();
                if (uniquePlaces.add(place)) {
                    return getPlacePreview(place);
                }
                return null;
            })
            .filter(placePreview -> placePreview != null)
            .toList();

    return uniquePlacePreviews;
}



    private static PlacePreview getPlacePreview(Place place) {
        return new PlacePreview(
                place.getId(),
                place.getImgUrl(),
                place.getTitle(),
                place.getAddress(),
                place.getOverview(),
                place.getMapX(),
                place.getMapY()
        );
    }
}
