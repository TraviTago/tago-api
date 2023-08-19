package com.tago.api.tripmember.application;


import com.tago.api.tripmember.dto.response.TripMemberJoinResponse;
import com.tago.domain.trip.domain.Trip;
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

    //존재하는 tripId인지 확인
    if (!tripCommandService.isValidTripId(tripId)){
        throw new IllegalArgumentException("Invalid tripId");
    }

        //현재 인원수와 최대 인원수 비교하기
        Trip trip = tripCommandService.getTripById(tripId);

        if(trip.getCurrent_member()>=trip.getMax_member()){
            throw new IllegalArgumentException("The trip has reached its maximum members limit");
        }

        //현재이원수 증가
        tripCommandService.incrementCurrentMember(trip);

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








