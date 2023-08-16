package com.tago.api.tripmember.application;


import com.tago.api.tripmember.dto.response.TripMemberJoinResponse;
import com.tago.domain.trip.domain.TripMember;
import com.tago.domain.trip.service.TripMemberCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TripMemberService {

    @Autowired
    private TripMemberCommandService tripMemberCommandService;

    public TripMemberJoinResponse joinTrip(Long tripId, Long memberId){

        TripMember tripMember = create(tripId, memberId);
        return new TripMemberJoinResponse(tripMember.getId(),tripMember.getTripId(),tripMember.getMemberId());

    }

    private TripMember create (Long tripId, Long memberId){

        TripMember tripMember = TripMember.builder()
                .tripId(tripId)
                .memberId(memberId)
                .build();

        return tripMemberCommandService.save(tripMember);
    }

}




