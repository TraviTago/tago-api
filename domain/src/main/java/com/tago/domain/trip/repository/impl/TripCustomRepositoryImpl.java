package com.tago.domain.trip.repository.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tago.domain.driver.domain.Driver;
import com.tago.domain.driver.domain.QDriver;
import com.tago.domain.member.domain.Member;
import com.tago.domain.member.domain.QMember;
import com.tago.domain.member.domain.vo.Gender;
import com.tago.domain.tag.domain.QTag;
import com.tago.domain.trip.domain.Trip;
import com.tago.domain.trip.repository.TripCustomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.tago.domain.driver.domain.QDispatch.dispatch;
import static com.tago.domain.member.domain.QMember.member;
import static com.tago.domain.member.domain.QMemberTag.memberTag;
import static com.tago.domain.place.domain.QPlace.place;
import static com.tago.domain.tag.domain.QTag.tag;
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
    public List<Trip> findAll(Long cursorId, LocalDateTime cursorDate, int limit, Boolean sameGender, Boolean isPet, Gender memberGender) {
        List<Long> ids = queryFactory.select(trip.id)
                .from(trip)
                .where(
                        isNotDone(),
                        cursorGt(cursorId, cursorDate),
                        isNotOrigin(),
                        isSameGender(sameGender, memberGender),
                        isPet(isPet)
                )
                .orderBy(trip.dateTime.asc(), trip.id.asc())
                .limit(limit)
                .fetch();

        return queryFactory.selectFrom(trip)
                .distinct()
                .innerJoin(trip.tripPlaces, tripPlace).fetchJoin()
                .innerJoin(tripPlace.place, place).fetchJoin()
                .leftJoin(trip.tripMembers, tripMember)
                .where(trip.id.in(ids))
                .orderBy(trip.dateTime.asc(), trip.id.asc(), tripPlace.order.asc())
                .fetch();
    }

    @Override
    public List<Trip> findAllByNotDispatch(Long cursorId, LocalDateTime cursorDate, int limit) {
        List<Long> ids = queryFactory.select(trip.id)
                .from(trip)
                .leftJoin(dispatch).on(trip.id.eq(dispatch.trip.id))
                .where(
                        isNotDone(),
                        cursorGt(cursorId, cursorDate),
                        notExistsDispatch()
                )
                .orderBy(trip.dateTime.asc(), trip.id.asc())
                .limit(limit)
                .fetch();

        return queryFactory.selectFrom(trip)
                .innerJoin(trip.tripPlaces, tripPlace).fetchJoin()
                .innerJoin(tripPlace.place, place).fetchJoin()
                .leftJoin(trip.tripMembers, tripMember)
                .where(trip.id.in(ids))
                .orderBy(trip.dateTime.asc(), trip.id.asc(), tripPlace.order.asc())
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

    public Trip findByTripTag(Long memberId, Gender memberGender){
        List<Long> joinedTripIds = queryFactory
                .select(trip.id)
                .from(tripMember)
                .innerJoin(tripMember.trip, trip)
                .innerJoin(tripMember.member, member)
                .where(isJoined(memberId))
                .fetch();

        List<Long> tripIds = queryFactory
                .select(trip.id)
                .from(trip)
                .where(
                        isNotOrigin(),
                        isNotDone(),
                        trip.id.notIn(joinedTripIds),
                        isSameGender(false, memberGender)
                )
                .fetch();

        Long tripId = queryFactory
                .select(tripTag.trip.id)
                .from(tripTag)
                .innerJoin(memberTag).on(memberTag.tag.eq(tripTag.tag))
                .where(
                        memberTag.member.id.eq(memberId),
                        tripIdIn(tripIds)
                )
                .groupBy(tripTag.trip.id)
                .orderBy(tripTag.tag.id.count().desc())
                .fetchFirst();

        return queryFactory
                .selectFrom(trip)
                .innerJoin(trip.tripPlaces, tripPlace).fetchJoin()
                .innerJoin(tripPlace.place, place).fetchJoin()
                .where(
                        tripIdEq(tripId),
                        tripIdIn(tripIds)
                )
                .orderBy(
                        tripPlace.order.asc(),
                        Expressions.numberTemplate(Double.class, "function('rand')").asc()
                )
                .fetchFirst();
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

    public List<Trip> findAllByDriver(Driver driver) {
        return queryFactory.select(trip)
                .from(dispatch)
                .innerJoin(dispatch.trip, trip)
                .innerJoin(dispatch.driver, QDriver.driver)
                .innerJoin(trip.tripPlaces, tripPlace).fetchJoin()
                .innerJoin(tripPlace.place, place).fetchJoin()
                .where(driverEq(driver))
                .orderBy(trip.dateTime.asc(), tripPlace.order.asc())
                .fetch();
    }

    public Optional<Trip> findByIdFetchTripMember(Long tripId) {
        return Optional.ofNullable(queryFactory.selectFrom(trip)
                .leftJoin(trip.tripMembers, tripMember)
                .leftJoin(tripMember.member, member)
                .where(tripIdEq(tripId))
                .fetchOne());
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
        return id != null ? trip.id.eq(id) : null;
    }

    private BooleanExpression tripIdIn(List<Long> ids) {
        return ids.isEmpty() ? null : trip.id.in(ids);
    }

    private BooleanExpression isSameGender(Boolean sameGender, Gender memberGender){
        return sameGender ? trip.condition.sameGender.eq(true).and(isSameGenderTripAndMember(memberGender))
                : trip.condition.sameGender.eq(false).or(isSameGenderTripAndMember(memberGender));
    }

    private BooleanExpression isSameGenderTripAndMember(Gender memberGender) {
        return trip.member.profile.gender.eq(memberGender);
    }
    private BooleanExpression isPet(Boolean isPet) {
        return isPet ? trip.condition.isPet.eq(false) : null;
    }

    private BooleanExpression memberEq(Member member) {
        return QMember.member.eq(member);
    }

    private BooleanExpression driverEq(Driver driver) {
        return QDriver.driver.eq(driver);
    }

    private BooleanExpression notExistsDispatch() {
        return dispatch.id.isNull();
    }

    private BooleanExpression isNotOrigin() {
        return trip.origin.eq(false);
    }

    private BooleanExpression isJoined(Long memberId) {
        return tripMember.member.id.eq(memberId);
    }
}
