package com.tago.domain.trip.service;

import com.tago.domain.member.domain.Member;
import com.tago.domain.member.domain.vo.Authority;
import com.tago.domain.member.domain.vo.OAuthProvider;

import com.tago.domain.member.service.MemberQueryService;
import com.tago.domain.trip.domain.Trip;
import com.tago.domain.trip.domain.TripMember;
import com.tago.domain.trip.repository.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class TripMemberCreateService {

    public final TripMemberCommandService tripMemberCommandService;
    public final TripQueryService tripQueryService;
    public final MemberQueryService memberQueryService;


    public TripMember create(Long trip_id,Long member_id){

        Trip trip = tripQueryService.findByID(trip_id);
        Member member = memberQueryService.findById(member_id);

        TripMember tripMember = TripMember.builder()
                .trip(trip)
                .member(member)
                .build();

        return tripMemberCommandService.save(tripMember);
    }
}



