package com.tago.domain.place.repository.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tago.domain.place.dto.PlacePreviewDto;
import com.tago.domain.place.dto.QPlacePreviewDto;
import com.tago.domain.place.repository.PlaceCustomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nonapi.io.github.classgraph.json.JSONUtils;
import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

import static com.tago.domain.member.domain.QMemberTag.memberTag;
import static com.tago.domain.place.domain.QPlace.place;
import static com.tago.domain.place.domain.QPlaceTag.placeTag;

@Slf4j
@Repository
@RequiredArgsConstructor
public class PlaceCustomRepositoryImpl implements PlaceCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<PlacePreviewDto> findAll(Long cursorId, int limit) {
        return queryFactory.select(new QPlacePreviewDto(
                    place.id,
                    place.imgUrl,
                    place.title,
                    place.address
                )).from(place)
                .where(cursorGt(cursorId))
                .limit(limit)
                .fetch();
    }

    @Override
    public List<PlacePreviewDto> findByTitleKeyword (String keyword){
        return queryFactory.select(new QPlacePreviewDto(
                    place.id,
                    place.imgUrl,
                    place.title,
                    place.address
                )).from(place)
                .where(searchTitle(keyword))
                .fetch();

    }




    public List<PlacePreviewDto> findRecommendedPlaces(Long memberId){

        List<Long> memberTags = queryFactory
                .select(memberTag.tag.id)
                .from(memberTag)
                .where(memberTag.member.id.eq(memberId))
                .fetch();

        if (memberTags.isEmpty()) {
            log.warn("No tags found for member with ID: {}", memberId);
            return Collections.emptyList();
        }

        return queryFactory
                .select(new QPlacePreviewDto(place.id,place.imgUrl,place.title,place.address))
                .from(place)
                .innerJoin(placeTag).on(place.id.eq(placeTag.place.id).and(placeTag.tag.id.in(memberTags)))
                .groupBy(place.id)
                .orderBy(placeTag.tag.id.count().desc())
                .limit(3)
                .fetch();
    }

    private BooleanExpression searchTitle(String keyword){
        return place.title.contains(keyword);
    }

    private BooleanExpression cursorGt(Long cursorId) {
        return place.id.gt(cursorId);
    }
}
