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

//    private static List<PlacePreview> getPlaces(List<CoursePlace> coursePlaces) {
//        return coursePlaces.stream()
//                .map(coursePlace -> getPlacePreview(coursePlace.getPlace()))
//                .toList();
//    }
private static List<PlacePreview> getPlaces(List<CoursePlace> coursePlaces) {
    // 중복을 제거하기 위해 Set을 사용합니다.
    Set<Place> uniquePlaces = new HashSet<>();

    // 중복을 제거한 Place 객체들을 담을 리스트를 생성합니다.
    List<PlacePreview> uniquePlacePreviews = coursePlaces.stream()
            .map(coursePlace -> {
                Place place = coursePlace.getPlace();
                if (uniquePlaces.add(place)) {
                    // Set에 추가된 경우에만 PlacePreview 객체를 생성합니다.
                    return getPlacePreview(place);
                }
                return null;
            })
            .filter(placePreview -> placePreview != null) // null을 제거합니다.
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
