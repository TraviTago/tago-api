package com.tago.domain.place.repository.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tago.domain.place.dto.PlacePreviewDto;
import com.tago.domain.place.dto.QPlacePreviewDto;
import com.tago.domain.place.repository.PlaceCustomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.tago.domain.place.domain.QPlace.place;

@Repository
@RequiredArgsConstructor
public class PlaceCustomRepositoryImpl implements PlaceCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<PlacePreviewDto> findAll(Long cursorId, int limit) {
        return queryFactory.select(new QPlacePreviewDto(
                    place.imgUrl,
                    place.title,
                    place.address
                )).from(place)
                .where(cursorGt(cursorId))
                .limit(limit)
                .fetch();
    }

    private BooleanExpression cursorGt(Long cursorId) {
        return place.id.gt(cursorId);
    }
}
