package com.tago.api.tripmember.application;


import com.tago.api.tripmember.dto.response.TripMemberJoinResponse;
import com.tago.domain.member.exception.MaxMemberLimitException;
import com.tago.domain.trip.domain.Trip;
import com.tago.domain.trip.domain.TripMember;
import com.tago.domain.trip.service.TripCommandService;
import com.tago.domain.trip.service.TripMemberCommandService;
import com.tago.domain.trip.service.TripQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TripMemberService {

    private final TripMemberCommandService tripMemberCommandService;
    private final TripCommandService tripCommandService;
    private final TripQueryService tripQueryService;

    public TripMemberJoinResponse joinTrip(Long tripId, Long memberId){

        //존재하는 여행인지 확인
        Trip trip = tripQueryService.findByID(tripId);

        //현재 인원수와 최대 인원수 비교하기
        if(trip.getCurrent_member()>=trip.getMax_member()){
            throw new MaxMemberLimitException();
        }

        //현재 인원수 증가
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








