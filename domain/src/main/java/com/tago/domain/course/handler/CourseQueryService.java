package com.tago.domain.course.handler;

import com.tago.domain.course.domain.Course;
import com.tago.domain.course.repository.CourseRepository;
import com.tago.domain.member.domain.vo.Favorite;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseQueryService {

    private final CourseRepository courseRepository;

    public Course findByPlaceIdAndCourseTag(Long placeId, List<Favorite> tags) {
        return courseRepository.findByPlaceIdAndCourseTag(placeId, tags);
    }
}
