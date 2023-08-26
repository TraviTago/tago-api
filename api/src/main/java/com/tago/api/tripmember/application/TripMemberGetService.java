package com.tago.api.tripmember.application;

import com.tago.api.tripmember.dto.response.TripMemberGetMemberResponse;
import com.tago.api.tripmember.dto.response.TripMemberGetTripResponse;
import com.tago.domain.member.domain.Member;
import com.tago.domain.member.service.MemberQueryService;
import com.tago.domain.trip.domain.Trip;
import com.tago.domain.trip.service.TripQueryService;
import com.tago.domain.tripmember.dto.MyTripDto;
import com.tago.domain.tripmember.dto.TripMemberDto;
import com.tago.domain.tripmember.service.TripMemberQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TripMemberGetService {

    private final MemberQueryService memberQueryService;
    private final TripQueryService tripQueryService;
    private final TripMemberQueryService tripMemberQueryService;

    @Transactional(readOnly = true)
    public TripMemberGetTripResponse getTripsByMember(Long memberId) {
        Member member = memberQueryService.findById(memberId);
        List<MyTripDto> trips = tripMemberQueryService.findTripsByMember(member);
        return new TripMemberGetTripResponse(trips);
    }

    @Transactional(readOnly = true)
    public TripMemberGetMemberResponse getMembersByTrip(Long tripId) {
        Trip trip = tripQueryService.findById(tripId);
        List<TripMemberDto> members = tripMemberQueryService.findMembersByTrip(trip);
        return new TripMemberGetMemberResponse(members);
    }
}
