package com.tago.domain.tripmember.repository.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tago.domain.member.domain.Member;
import com.tago.domain.member.domain.QMember;
import com.tago.domain.trip.domain.Trip;
import com.tago.domain.tripmember.repository.TripMemberCustomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.tago.domain.place.domain.QPlace.place;
import static com.tago.domain.trip.domain.QTrip.trip;
import static com.tago.domain.trip.domain.QTripPlace.*;
import static com.tago.domain.tripmember.domain.QTripMember.tripMember;

@Repository
@RequiredArgsConstructor
public class TripMemberCustomRepositoryImpl implements TripMemberCustomRepository {

    private final JPAQueryFactory queryFactory;

    public List<Trip> findTripsByMember(Member member) {
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

    private BooleanExpression memberEq(Member member) {
        return tripMember.member.eq(member);
    }
}
