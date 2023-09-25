package com.tago.api.course.dto.response;

import com.tago.domain.place.dto.PlacePreviewDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CourseRecommendResponse {
    private String imgUrl;
    private int totalTime;
    private List<PlacePreview> places;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PlacePreview {
        private Long id;
        private String imageUrl;
        private String title;
        private String address;
        private String overview;
        private double mapX;
        private double mapY;
    }
}
