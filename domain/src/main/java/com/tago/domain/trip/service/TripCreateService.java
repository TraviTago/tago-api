package com.tago.domain.trip.service;

import com.tago.domain.member.domain.Member;
import com.tago.domain.trip.domain.Trip;
import com.tago.domain.trip.domain.vo.Condition;
import com.tago.domain.trip.dto.TripCreateDto;
import com.tago.domain.trip.handler.TripCommandService;
import com.tago.domain.tripmember.service.TripMemberCreateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TripCreateService {

    public static int TOTAL_TIME = 480;
    private final TripCommandService tripCommandService;
    private final TripMemberCreateService tripMemberCreateService;
    private final TripTagCreateService tripTagCreateService;
    private final TripPlaceCreateService tripPlaceCreateService;

    public Trip create(Member member, TripCreateDto dto) {
        Condition condition = Condition.builder()
                .sameGender(dto.getSameGender())
                .sameAge(dto.getSameAge())
                .isPet(dto.getIsPet())
                .build();

        Trip trip = Trip.builder()
                .name(dto.getName())
                .dateTime(dto.getDateTime())
                .meetPlace(dto.getMeetPlace())
                .totalTime(TOTAL_TIME)
                .maxCnt(dto.getMaxCnt())
                .currentCnt(dto.getCurrentCnt())
                .condition(condition)
                .member(member)
                .origin(false)
                .build();

        trip.addTripPlaces(tripPlaceCreateService.createTripPlaces(trip, dto.getPlaces()));
        trip.addTripTags(tripTagCreateService.createTripTags(trip, dto.getTypes()));
        tripMemberCreateService.action(trip.getId(), member.getId());

        return tripCommandService.save(trip);
    }
}
