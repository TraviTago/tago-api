package com.tago.domain.place.repository.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tago.domain.place.dto.PlacePreviewDto;
import com.tago.domain.place.dto.PopularPlaceDto;
import com.tago.domain.place.dto.QPlacePreviewDto;
import com.tago.domain.place.dto.QPopularPlaceDto;
import com.tago.domain.place.repository.PlaceCustomRepository;
import com.tago.domain.tag.domain.QTag;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.tago.domain.member.domain.QMemberTag.memberTag;
import static com.tago.domain.place.domain.QPlace.place;
import static com.tago.domain.place.domain.QPlaceTag.placeTag;
import static com.tago.domain.tag.domain.QTag.tag;

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

    public List<PlacePreviewDto> findByPlaceTag(Long memberId){
        List<Long> memberTags = queryFactory
                .select(memberTag.tag.id)
                .from(memberTag)
                .where(memberTag.member.id.eq(memberId))
                .fetch();

        return queryFactory
                .select(new QPlacePreviewDto(
                        place.id,
                        place.imgUrl,
                        place.title,
                        place.address
                )).from(placeTag)
                .innerJoin(placeTag.place, place)
                .innerJoin(placeTag.tag, tag)
                .where(tag.id.in(memberTags))
                .groupBy(place.id)
                .orderBy(tag.id.count().desc())
                .limit(5)
                .fetch();
    }

    public List<PopularPlaceDto> findPopularPlaces(){

        return queryFactory
                .select(new QPopularPlaceDto(place.id,place.imgUrl,place.title,place.address,place.visit))
                .from(place)
                .orderBy(place.visit.desc())
                .limit(10)
                .fetch();
    }

    private BooleanExpression searchTitle(String keyword){
        return place.title.contains(keyword);
    }

    private BooleanExpression cursorGt(Long cursorId) {
        return place.id.gt(cursorId);
    }
}
