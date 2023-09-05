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
import static org.apache.logging.log4j.util.Strings.isNotEmpty;

@Repository
@RequiredArgsConstructor
public class TripCustomRepositoryImpl implements TripCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Trip> findAllFetchTripPlaceAndPlace(Long cursorId, LocalDateTime cursorDate, int limit) {
        List<Long> ids = queryFactory.select(trip.id)
                .from(trip)
                .where(isNotDone(), cursorGt(cursorId, cursorDate))
                .limit(limit)
                .fetch();

        return queryFactory.selectFrom(trip)
                .innerJoin(trip.tripPlaces, tripPlace).fetchJoin()
                .innerJoin(tripPlace.place, place).fetchJoin()
                .where(trip.id.in(ids))
                .orderBy(trip.dateTime.asc(), tripPlace.order.asc())
                .fetch();
    }

//    @Override
//    public List<Trip> findByPlaceTitleKeywordContain(String keyword, Long cursorId, LocalDateTime cursorDate, int limit) {
//        List<Long> ids = queryFactory.select(tripPlace.trip.id)
//                .from(tripPlace)
//                .where(
//                        containPlaceTitle(keyword),
//                        isNotDone(),
//                        cursorGt(cursorId, cursorDate)
//                )
//                .distinct()
//                .limit(limit)
//                .fetch();
//
//        return queryFactory.selectFrom(trip)
//                .innerJoin(trip.tripPlaces, tripPlace).fetchJoin()
//                .innerJoin(tripPlace.place, place).fetchJoin()
//                .where(trip.id.in(ids))
//                .orderBy(trip.dateTime.asc(), tripPlace.order.asc())
//                .fetch();
//    }

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

    private BooleanExpression containPlaceTitle(String keyword) {
        return isNotEmpty(keyword) ? place.title.containsIgnoreCase(keyword) : null;
    }
}
