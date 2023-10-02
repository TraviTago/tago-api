package com.tago.api.tripmember.application;


import com.tago.api.common.exception.AlreadyExistsTripMemberException;
import com.tago.domain.member.domain.Member;
import com.tago.domain.member.handler.MemberQueryService;
import com.tago.domain.trip.domain.Trip;
import com.tago.domain.tripmember.handler.TripMemberQueryService;
import com.tago.domain.tripmember.service.TripMemberCreateService;
import com.tago.domain.trip.handler.TripQueryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TripMemberSaveService {

    private final TripMemberCreateService tripMemberCreateService;
    private final TripMemberQueryService tripMemberQueryService;
    private final TripQueryService tripQueryService;
    private final MemberQueryService memberQueryService;

    @Transactional
    public void joinTrip(Long tripId, Long memberId){
        isAlreadyJoined(tripId, memberId);
        Trip trip = tripQueryService.findByIdFetchTripMember(tripId);
        Member member = memberQueryService.findById(memberId);
        trip.join(member);
    }

    private void isAlreadyJoined(Long tripId, Long memberId) {
        if (tripMemberQueryService.existsByTripIdAndMemberId(tripId, memberId)) {
            throw new AlreadyExistsTripMemberException();
        }
    }
}








