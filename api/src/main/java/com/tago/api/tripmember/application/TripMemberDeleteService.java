package com.tago.api.tripmember.application;

import com.tago.domain.tripmember.domain.TripMember;
import com.tago.domain.tripmember.service.TripMemberCommandService;
import com.tago.domain.tripmember.service.TripMemberQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TripMemberDeleteService {

    private final TripMemberQueryService tripMemberQueryService;
    private final TripMemberCommandService tripMemberCommandService;

    @Transactional
    public void deleteTripMember(Long tripId,Long memberId){
        TripMember tripmember = tripMemberQueryService.findByTripIdAndMemberId(tripId,memberId);
        tripMemberCommandService.delete(tripmember);
    }
}
