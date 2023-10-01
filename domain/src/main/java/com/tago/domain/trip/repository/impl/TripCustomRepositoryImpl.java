package com.tago.domain.trip.repository.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tago.domain.member.domain.Member;
import com.tago.domain.member.domain.QMember;
import com.tago.domain.trip.domain.Trip;
import com.tago.domain.trip.repository.TripCustomRepository;
import com.tago.domain.tripmember.domain.QTripMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.time.LocalDateTime;
import java.util.List;

import static com.tago.domain.member.domain.QMemberTag.memberTag;
import static com.tago.domain.place.domain.QPlace.place;
import static com.tago.domain.trip.domain.QTrip.trip;
import static com.tago.domain.trip.domain.QTripPlace.tripPlace;
import static com.tago.domain.trip.domain.QTripTag.tripTag;
import static com.tago.domain.tripmember.domain.QTripMember.tripMember;
import static org.apache.logging.log4j.util.Strings.isNotEmpty;

@Repository
@RequiredArgsConstructor
public class TripCustomRepositoryImpl implements TripCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Trip> findAllFetchTripPlaceAndPlace(Long cursorId, LocalDateTime cursorDate, int limit, Boolean sameGender, Boolean isPet) {
        List<Long> ids = queryFactory.select(trip.id)
                .from(trip)
                .where(
                        isNotDone(),
                        cursorGt(cursorId, cursorDate),
                        isSameGender(sameGender),
                        isPet(isPet)
                )
                .limit(limit)
                .fetch();

        return queryFactory.selectFrom(trip)
                .distinct()
                .innerJoin(trip.tripPlaces, tripPlace).fetchJoin()
                .innerJoin(tripPlace.place, place).fetchJoin()
                .innerJoin(trip.tripMembers, tripMember)
                .where(trip.id.in(ids))
                .orderBy(trip.dateTime.asc(), tripPlace.order.asc())
                .fetch();
    }

    @Override
    public List<Trip> findByPlaceTitleKeywordContain(String keyword, Long cursorId, LocalDateTime cursorDate, int limit) {
        List<Long> ids = queryFactory.select(tripPlace.trip.id)
                .from(tripPlace)
                .where(
                        containPlaceTitle(keyword),
                        isNotDone(),
                        cursorGt(cursorId, cursorDate)
                )
                .distinct()
                .limit(limit)
                .fetch();

        return queryFactory.selectFrom(trip)
                .innerJoin(trip.tripPlaces, tripPlace).fetchJoin()
                .innerJoin(tripPlace.place, place).fetchJoin()
                .where(trip.id.in(ids))
                .orderBy(trip.dateTime.asc(), tripPlace.order.asc())
                .fetch();
    }

    public Trip findByTripTag(Long memberId){
        List<Long> tagIds = queryFactory
                .select(memberTag.tag.id)
                .from(memberTag)
                .where(memberTag.member.id.eq(memberId))
                .fetch();

        Long tripId = queryFactory
                .select(tripTag.trip.id)
                .from(tripTag)
                .where(tagIdIn(tagIds))
                .groupBy(tripTag.trip.id)
                .orderBy(tripTag.tag.id.count().desc())
                .fetchFirst();

        return queryFactory
                .selectFrom(trip)
                .innerJoin(trip.tripPlaces, tripPlace).fetchJoin()
                .innerJoin(tripPlace.place, place).fetchJoin()
                .where(tripIdEq(tripId))
                .orderBy(tripPlace.order.asc())
                .fetchOne();
    }

    public List<Trip> findAllByMember(Member member) {
        return queryFactory.select(trip)
                .from(tripMember)
                .innerJoin(tripMember.trip, trip)
                .innerJoin(tripMember.member, QMember.member)
                .leftJoin(trip.tripPlaces, tripPlace).fetchJoin()
                .leftJoin(tripPlace.place, place).fetchJoin()
                .where(memberEq(member))
                .orderBy(trip.dateTime.asc(), tripPlace.order.asc())
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

    private BooleanExpression containPlaceTitle(String keyword) {
        return isNotEmpty(keyword) ? place.title.containsIgnoreCase(keyword) : null;
    }

    private BooleanExpression tripIdEq(Long id) {
        return trip.id.eq(id);
    }

    private BooleanExpression tagIdIn(List<Long> ids) {
        return tripTag.tag.id.in(ids);
    }

    private BooleanExpression isSameGender(Boolean sameGender){
        return sameGender ? trip.condition.sameGender.eq(true) : null ;
    }

    private BooleanExpression isPet(Boolean isPet) {
        return isPet ? trip.condition.isPet.eq(true) : null;
    }

    private BooleanExpression memberEq(Member member) {
        return QMember.member.eq(member);
    }
}
