package com.tago.api.tripmember.application;


import com.tago.api.tripmember.dto.response.TripMemberGetResponse;
import com.tago.domain.tripmember.dto.TripMemberDto;
import com.tago.domain.tripmember.exception.AlreadyExistsTripMemberException;
import com.tago.domain.member.domain.Member;
import com.tago.domain.member.handler.MemberQueryService;
import com.tago.domain.trip.domain.Trip;
import com.tago.domain.trip.handler.TripQueryService;
import com.tago.domain.tripmember.handler.TripMemberQueryService;
import com.tago.domain.tripmember.service.factory.TripMemberService;
import com.tago.domain.tripmember.service.factory.TripMemberServiceFactory;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TripMemberSaveService {

    private final TripMemberServiceFactory tripMemberServiceFactory;
    private final TripMemberQueryService tripMemberQueryService;
    private final TripQueryService tripQueryService;
    private final MemberQueryService memberQueryService;

    @Transactional
    public void joinOrLeave(Long tripId, Long memberId, String state){
        Trip trip = tripQueryService.findByIdFetchTripMember(tripId);
        Member member = memberQueryService.findById(memberId);
        TripMemberService service = tripMemberServiceFactory.getInstance(state);
        service.action(trip, member);
    }

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public TripMemberGetResponse getMembersByTrip(Long tripId) {
        Trip trip = tripQueryService.findByIdFetchTripMember(tripId);
        List<TripMemberDto> members = tripMemberQueryService.findMembersByTrip(trip);
        return new TripMemberGetResponse(members);
    }
}








