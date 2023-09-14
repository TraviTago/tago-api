package com.tago.domain.course.repository;

import com.tago.domain.course.domain.Course;
import com.tago.domain.member.domain.vo.Favorite;

import java.util.List;

public interface CourseCustomRepository {
    Course findByPlaceIdAndCourseTag(Long placeId, List<Favorite> tags);
}
