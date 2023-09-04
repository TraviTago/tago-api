package com.tago.api.trip.application;

import com.tago.api.trip.dto.response.TripGetOneResponse;
import com.tago.domain.trip.domain.Trip;
import com.tago.domain.trip.dto.TripPlaceDto;
import com.tago.domain.trip.service.TripPlaceQueryService;
import com.tago.domain.trip.service.TripQueryService;
import com.tago.domain.tripmember.service.TripMemberQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TripPlaceService {

    private final TripQueryService tripQueryService;
    private final TripPlaceQueryService tripPlaceQueryService;
    private final TripMemberQueryService tripMemberQueryService;

    @Transactional(readOnly = true)
    public TripGetOneResponse getTripAndPlaces(Long memberId, Long tripId) {
        Trip trip = tripQueryService.findById(tripId);
        List<TripPlaceDto> tripPlaces = tripPlaceQueryService.findAll(trip);

        return new TripGetOneResponse(
                trip.getName(),
                trip.getCurrentCnt(),
                trip.getMaxCnt(),
                isJoined(tripId, memberId),
                tripPlaces
        );
    }

    private Boolean isJoined(Long tripId, Long memberId) {
        return tripMemberQueryService.existsByTripIdAndMemberId(tripId, memberId);
    }
}
