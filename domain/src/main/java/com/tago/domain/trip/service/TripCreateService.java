package com.tago.domain.trip.service;

import com.tago.domain.member.domain.Member;
import com.tago.domain.trip.domain.Trip;
import com.tago.domain.trip.domain.vo.Condition;
import com.tago.domain.trip.dto.TripCreateDto;
import com.tago.domain.tripmember.service.TripMemberCreateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TripCreateService {

    public static int TOTAL_TIME = 480;
    private final TripCommandService tripCommandService;

    public Trip create(Member member, TripCreateDto tripCreateDto) {
        Condition condition = Condition.builder()
                .sameGender(tripCreateDto.getSameGender())
                .sameAge(tripCreateDto.getSameAge())
                .isPet(tripCreateDto.getIsPet())
                .build();

        Trip trip = Trip.builder()
                .name(tripCreateDto.getName())
                .dateTime(tripCreateDto.getDateTime())
                .meetPlace(tripCreateDto.getMeetPlace())
                .totalTime(TOTAL_TIME)
                .maxCnt(tripCreateDto.getMaxCnt())
                .currentCnt(tripCreateDto.getCurrentCnt())
                .condition(condition)
                .member(member)
                .build();

        return tripCommandService.save(trip);
    }
}
