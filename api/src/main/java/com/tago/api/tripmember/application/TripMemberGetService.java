package com.tago.api.tripmember.application;

import com.tago.api.tripmember.dto.response.TripMemberGetResponse;
import com.tago.domain.trip.domain.Trip;
import com.tago.domain.trip.handler.TripQueryService;
import com.tago.domain.tripmember.dto.TripMemberDto;
import com.tago.domain.tripmember.handler.TripMemberQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TripMemberGetService {

    private final TripQueryService tripQueryService;
    private final TripMemberQueryService tripMemberQueryService;

    @Transactional(readOnly = true)
    public TripMemberGetResponse getMembersByTrip(Long tripId) {
        Trip trip = tripQueryService.findById(tripId);
        List<TripMemberDto> members = tripMemberQueryService.findMembersByTrip(trip);
        return new TripMemberGetResponse(members);
    }
}
