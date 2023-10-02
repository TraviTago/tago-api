package com.tago.domain.course.repository.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tago.domain.course.domain.Course;
import com.tago.domain.course.exception.CourseNotFoundException;
import com.tago.domain.course.repository.CourseCustomRepository;
import com.tago.domain.member.domain.vo.Favorite;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.tago.domain.course.domain.QCourse.course;
import static com.tago.domain.course.domain.QCoursePlace.coursePlace;
import static com.tago.domain.course.domain.QCourseTag.courseTag;
import static com.tago.domain.place.domain.QPlace.place;
import static com.tago.domain.tag.domain.QTag.tag;

@Repository
@RequiredArgsConstructor
public class CourseCustomRepositoryImpl implements CourseCustomRepository {

    public final JPAQueryFactory queryFactory;

    @Override
    public Course findByPlaceIdAndCourseTag(Long placeId, List<Favorite> tags) {
        List<Long> ids = queryFactory.select(course.id)
                .distinct()
                .from(coursePlace)
                .innerJoin(coursePlace.course, course)
                .innerJoin(coursePlace.place, place)
                .where(placeIdEq(placeId))
                .fetch();

        Long id = queryFactory.select(course.id)
                .distinct()
                .from(courseTag)
                .innerJoin(courseTag.course, course)
                .innerJoin(courseTag.tag, tag)
                .where(courseIdIn(ids), tagTypeIn(tags))
                .groupBy(course.id)
                .orderBy(tag.id.count().desc())
                .fetchFirst();

        return queryFactory.selectFrom(course)
                .distinct()
                .innerJoin(course.coursePlaces, coursePlace).fetchJoin()
                .innerJoin(coursePlace.place, place).fetchJoin()
                .where(courseIdEq(id, placeId))
                .orderBy(coursePlace.order.asc())
                .fetchFirst();
    }

    private BooleanExpression placeIdEq(Long placeId) {
        return placeId > 0 ? place.id.eq(placeId) : null;
    }

    private BooleanExpression courseIdEq(Long courseId, Long placeId) {
        return courseId != null ? course.id.eq(courseId) : placeIdEq(placeId);
    }

    private BooleanExpression courseIdIn(List<Long> courseIds) {
        return course.id.in(courseIds);
    }

    private BooleanExpression tagTypeIn(List<Favorite> tags) {
        return tag.type.in(tags);
    }
}
