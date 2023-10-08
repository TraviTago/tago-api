package com.tago.domain.driver.repository.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tago.domain.driver.dto.PassengerDto;
import com.tago.domain.driver.dto.QPassengerDto;
import com.tago.domain.driver.repository.PassengerRepository;
import com.tago.domain.trip.domain.QTrip;
import com.tago.domain.trip.domain.Trip;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.tago.domain.member.domain.QMember.member;
import static com.tago.domain.tripmember.domain.QTripMember.tripMember;

@Repository
@RequiredArgsConstructor
public class PassengerRepositoryImpl implements PassengerRepository {

    private final JPAQueryFactory queryFactory;

    public List<PassengerDto> findPassengerByTrip(Trip trip){
        return queryFactory.select(new QPassengerDto(
                        member.id,
                        member.profile.imgUrl,
                        member.phoneNumber,
                        member.name,
                        member.profile.mbti,
                        member.profile.ageRange,
                        member.profile.gender,
                        member.profile.tripTypes
                )).from(tripMember)
                .innerJoin(tripMember.trip, QTrip.trip)
                .innerJoin(tripMember.member, member)
                .where(tripEq(trip))
                .fetch();

    }

    private BooleanExpression tripEq(Trip trip) {
        return tripMember.trip.eq(trip);
    }

}
