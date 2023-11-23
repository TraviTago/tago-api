package com.tago.api.tripmember.application;


import com.tago.api.tripmember.dto.response.TripMemberGetResponse;
import com.tago.domain.tripmember.dto.TripMemberDto;
import com.tago.domain.member.domain.Member;
import com.tago.domain.member.handler.MemberQueryService;
import com.tago.domain.trip.domain.Trip;
import com.tago.domain.trip.handler.TripQueryService;
import com.tago.domain.tripmember.handler.TripMemberQueryService;
import com.tago.domain.tripmember.service.TripMemberFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TripMemberSaveService {

    private final TripMemberFacade tripMemberFacade;
    private final TripMemberQueryService tripMemberQueryService;
    private final TripQueryService tripQueryService;

    @Transactional
    public void joinOrLeave(Long tripId, Long memberId, String state){
        tripMemberFacade.createOrDelete(tripId, memberId, state);
    }

    @Transactional(readOnly = true)
    public TripMemberGetResponse getMembersByTrip(Long tripId) {
        Trip trip = tripQueryService.findByIdFetchTripMember(tripId);
        List<TripMemberDto> members = tripMemberQueryService.findMembersByTrip(trip);
        return new TripMemberGetResponse(members);
    }
}








