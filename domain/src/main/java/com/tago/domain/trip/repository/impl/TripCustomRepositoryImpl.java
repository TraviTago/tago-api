package com.tago.domain.trip.repository.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tago.domain.trip.domain.Trip;
import com.tago.domain.trip.repository.TripCustomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static com.tago.domain.place.domain.QPlace.place;
import static com.tago.domain.trip.domain.QTrip.trip;
import static com.tago.domain.trip.domain.QTripPlace.tripPlace;

@Repository
@RequiredArgsConstructor
public class TripCustomRepositoryImpl implements TripCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Trip> findAllFetchTripPlaceAndPlace(Long cursorId, LocalDateTime cursorDate, int limit) {
        return queryFactory.selectFrom(trip)
                .leftJoin(trip.tripPlaces, tripPlace).fetchJoin()
                .leftJoin(tripPlace.place, place).fetchJoin()
                .where(
                        isNotDone(),
                        cursorGt(cursorId, cursorDate)
                )
                .orderBy(trip.dateTime.asc(), tripPlace.order.asc())
                .limit(limit)
                .fetch();
    }

    private BooleanExpression cursorGt(Long cursorId, LocalDateTime cursorDate) {
        return cursorIdAndDateGt(cursorId, cursorDate).or(cursorDateGt(cursorDate));
    }

    private BooleanExpression cursorIdAndDateGt(Long cursorId, LocalDateTime cursorDate) {
        return trip.id.gt(cursorId).and(trip.dateTime.eq(cursorDate));
    }
    private BooleanExpression cursorDateGt(LocalDateTime cursorDate) {
        return trip.dateTime.gt(cursorDate); // trip.datetime > cursorDate
    }

    private BooleanExpression isNotDone() {
        return trip.dateTime.gt(LocalDateTime.now()); // trip.datetime > now
    }
}
