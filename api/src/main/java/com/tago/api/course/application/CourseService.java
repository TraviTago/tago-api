package com.tago.api.course.application;

import com.tago.api.common.mapper.CourseDtoMapper;
import com.tago.api.course.dto.response.CourseRecommendResponse;
import com.tago.domain.course.domain.Course;
import com.tago.domain.course.handler.CourseQueryService;
import com.tago.domain.tag.domain.vo.TagType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseQueryService courseQueryService;

    @Transactional(readOnly = true)
    public CourseRecommendResponse recommend(Long placeId, List<String> tags) {
        Course course = courseQueryService.findByPlaceIdAndCourseTag(placeId, TagType.from(tags));
        return CourseDtoMapper.toDto(course, placeId);
    }
}
