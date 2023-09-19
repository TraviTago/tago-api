package com.tago.domain.tripmember.service;


import com.tago.domain.member.domain.Member;
import com.tago.domain.trip.domain.Trip;
import com.tago.domain.tripmember.domain.TripMember;
import com.tago.domain.tripmember.handler.TripMemberCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class TripMemberCreateService {

    public final TripMemberCommandService tripMemberCommandService;

    public TripMember create(Trip trip, Member member) {
        TripMember tripMember = TripMember.builder()
                .trip(trip)
                .member(member)
                .build();

        return tripMemberCommandService.save(tripMember);
    }
}



