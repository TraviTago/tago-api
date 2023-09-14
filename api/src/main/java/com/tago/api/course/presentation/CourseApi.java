package com.tago.api.course.presentation;

import com.tago.api.common.dto.ResponseDto;
import com.tago.api.course.application.CourseService;
import com.tago.api.course.dto.request.CourseRecommendRequest;
import com.tago.api.course.dto.response.CourseRecommendResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CourseApi {

    private final CourseService courseService;

    @GetMapping("/courses/recommend")
    public ResponseEntity<CourseRecommendResponse> recommend(
            @RequestBody CourseRecommendRequest request
    ) {
        CourseRecommendResponse response = courseService.recommend(request);
        return ResponseDto.ok(response);
    }
}
