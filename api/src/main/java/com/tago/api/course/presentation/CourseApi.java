package com.tago.api.course.presentation;

import com.tago.api.common.dto.ResponseDto;
import com.tago.api.course.application.CourseService;
import com.tago.api.course.dto.response.CourseRecommendResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CourseApi {

    private final CourseService courseService;

    @GetMapping("/courses/recommend")
    public ResponseEntity<CourseRecommendResponse> recommend(
            @RequestParam(value = "placeId", defaultValue = "-1") Long placeId,
            @RequestParam("tags") List<String> tags
    ) {
        CourseRecommendResponse response = courseService.recommend(placeId, tags);
        return ResponseDto.ok(response);
    }
}
