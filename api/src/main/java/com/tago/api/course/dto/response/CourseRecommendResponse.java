package com.tago.api.course.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CourseRecommendResponse {
    private Long courseId;
    private String imgUrl;
    private int totalTime;
    private List<String> places;
}
