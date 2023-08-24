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

    public TripMember create(Long trip_id,Long member_id){
        TripMember tripMember = TripMember.builder()
                .tripId(trip_id)
                .memberId(member_id)
                .build();

        return tripMemberCommandService.save(tripMember);
    }
}



