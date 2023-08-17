package com.tago.api.tripmember.application;


import com.tago.api.tripmember.dto.response.TripMemberJoinResponse;
import com.tago.domain.trip.domain.TripMember;
import com.tago.domain.trip.service.TripCommandService;
import com.tago.domain.trip.service.TripMemberCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TripMemberService {

//    @Autowired
//    private TripMemberCommandService tripMemberCommandService;
//    private TripCommandService tripCommandService;

    private final TripMemberCommandService tripMemberCommandService;
    private final TripCommandService tripCommandService;

    public TripMemberJoinResponse joinTrip(Long tripId, Long memberId){

    if (!tripCommandService.isValidTripId(tripId)){
        throw new IllegalArgumentException("Invalid tripId"); //예외 처리
    }

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








