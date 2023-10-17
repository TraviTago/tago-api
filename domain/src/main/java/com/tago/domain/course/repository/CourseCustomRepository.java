package com.tago.domain.course.repository;

import com.tago.domain.course.domain.Course;
import com.tago.domain.tag.domain.vo.TagType;

import java.util.List;

public interface CourseCustomRepository {
    Course findByPlaceIdAndCourseTag(Long placeId, List<TagType> tagTypes);
}
