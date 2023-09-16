package com.tago.domain.course.repository;

import com.tago.domain.course.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long>, CourseCustomRepository {

}


