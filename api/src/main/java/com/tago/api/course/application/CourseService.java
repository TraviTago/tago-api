package com.tago.api.course.application;

import com.tago.api.common.mapper.CourseDtoMapper;
import com.tago.api.course.dto.request.CourseRecommendRequest;
import com.tago.api.course.dto.response.CourseRecommendResponse;
import com.tago.domain.course.domain.Course;
import com.tago.domain.course.service.CourseQueryService;
import com.tago.domain.member.domain.vo.Favorite;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseQueryService courseQueryService;

    @Transactional(readOnly = true)
    public CourseRecommendResponse recommend(CourseRecommendRequest request) {
        Course course = courseQueryService.findByPlaceIdAndCourseTag(
                request.getPlaceId(),
                Favorite.from(request.getTripTags())
        );
        return CourseDtoMapper.toDto(course, request.getPlaceId());
    }
}
