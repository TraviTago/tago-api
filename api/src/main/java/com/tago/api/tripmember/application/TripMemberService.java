package com.tago.api.tripmember.application;


import com.tago.api.tripmember.dto.response.TripMemberJoinResponse;
import com.tago.domain.member.exception.MaxMemberLimitException;
import com.tago.domain.trip.domain.Trip;
import com.tago.domain.trip.domain.TripMember;
import com.tago.domain.trip.service.TripCommandService;
import com.tago.domain.trip.service.TripMemberCommandService;
import com.tago.domain.trip.service.TripMemberCreateService;
import com.tago.domain.trip.service.TripQueryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TripMemberService {

    private final TripMemberCreateService tripMemberCreateService;
    private final TripQueryService tripQueryService;

    @Transactional
    public TripMemberJoinResponse joinTrip(Long tripId, Long memberId){
        Trip trip = tripQueryService.findById(tripId);
        canJoinTrip(trip);
        trip.incrementCurrentMember();

        TripMember tripMember = tripMemberCreateService.create(tripId, memberId);

        return TripMemberJoinResponse.from(tripMember);
    }

    private void canJoinTrip(Trip trip){
        if(trip.isLimitMember()){
            throw new MaxMemberLimitException();
        }
    }
}








