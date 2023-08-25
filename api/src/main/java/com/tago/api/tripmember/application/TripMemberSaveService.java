package com.tago.api.tripmember.application;


import com.tago.api.tripmember.dto.response.TripMemberJoinResponse;
import com.tago.domain.member.domain.Member;
import com.tago.domain.member.service.MemberQueryService;
import com.tago.domain.trip.domain.Trip;
import com.tago.domain.tripmember.domain.TripMember;
import com.tago.domain.tripmember.service.TripMemberCreateService;
import com.tago.domain.trip.service.TripQueryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TripMemberSaveService {

    private final TripMemberCreateService tripMemberCreateService;
    private final TripQueryService tripQueryService;
    private final MemberQueryService memberQueryService;

    @Transactional
    public TripMemberJoinResponse joinTrip(Long tripId, Long memberId){
        Trip trip = tripQueryService.findById(tripId);
        Member member = memberQueryService.findById(memberId);

        trip.join();
        TripMember tripMember = tripMemberCreateService.create(trip, member);

        return new TripMemberJoinResponse(tripMember.getId());
    }
}








