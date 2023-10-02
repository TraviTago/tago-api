package com.tago.api.tripmember.application;

import com.tago.domain.member.domain.Member;
import com.tago.domain.member.handler.MemberQueryService;
import com.tago.domain.trip.domain.Trip;
import com.tago.domain.trip.handler.TripQueryService;
import com.tago.domain.tripmember.domain.TripMember;
import com.tago.domain.tripmember.handler.TripMemberCommandService;
import com.tago.domain.tripmember.handler.TripMemberQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TripMemberDeleteService {

    private final TripQueryService tripQueryService;
    private final MemberQueryService memberQueryService;

    @Transactional
    public void leaveTrip(Long tripId,Long memberId){
        Trip trip = tripQueryService.findByIdFetchTripMember(tripId);
        Member member = memberQueryService.findById(memberId);
        trip.leave(member);
    }
}
