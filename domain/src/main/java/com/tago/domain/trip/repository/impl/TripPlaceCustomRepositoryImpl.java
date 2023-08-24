package com.tago.domain.trip.repository.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tago.domain.trip.domain.Trip;
import com.tago.domain.trip.dto.QTripPlaceDto;
import com.tago.domain.trip.dto.TripPlaceDto;
import com.tago.domain.trip.repository.TripPlaceCustomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.tago.domain.place.domain.QPlace.place;
import static com.tago.domain.trip.domain.QTripPlace.tripPlace;

@Repository
@RequiredArgsConstructor
public class TripPlaceCustomRepositoryImpl implements TripPlaceCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<TripPlaceDto> findAllByTripFetchPlace(Trip trip) {
        return queryFactory.select(new QTripPlaceDto(
                    place.id,
                    place.title,
                    place.imgUrl,
                    place.overview,
                    place.mapX,
                    place.mapY
                )).from(tripPlace)
                .join(tripPlace.place, place)
                .where(tripEq(trip))
                .orderBy(tripPlace.order.asc())
                .fetch();
    }

    private BooleanExpression tripEq(Trip trip) {
        return tripPlace.trip.eq(trip);
    }
}
