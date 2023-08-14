package com.tago.domain.trip.service;

import com.tago.domain.member.domain.Member;
import com.tago.domain.member.domain.vo.Authority;
import com.tago.domain.member.domain.vo.OAuthProvider;

import com.tago.domain.trip.domain.TripMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class TripMemberCreateService {

    public final TripMemberCommandService tripMemberCommandService;

    private TripMember create(Long trip_id,Long member_id){
        TripMember tripMember = TripMember.builder()
                .trip_id(trip_id)
                .member_id(member_id)
                .build();

        return tripMemberCommandService.save(tripMember);
    }
}

