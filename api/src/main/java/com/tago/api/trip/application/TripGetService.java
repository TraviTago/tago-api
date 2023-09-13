package com.tago.api.trip.application;

import com.tago.api.common.dto.PageResponseDto;
import com.tago.api.common.mapper.TripStatusDtoMapper;
import com.tago.api.trip.dto.response.TripGetOneResponse;
import com.tago.api.trip.dto.response.TripStatusResponse;
import com.tago.domain.trip.domain.Trip;
import com.tago.domain.trip.dto.TripPlaceDto;
import com.tago.domain.trip.dto.TripPreviewDto;
import com.tago.domain.trip.service.TripPlaceQueryService;
import com.tago.domain.tripmember.dto.TripMemberDto;
import com.tago.domain.trip.service.TripQueryService;
import com.tago.domain.tripmember.service.TripMemberQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TripGetService {

    private final TripQueryService tripQueryService;
    private final TripPlaceQueryService tripPlaceQueryService;
    private final TripMemberQueryService tripMemberQueryService;

    @Transactional(readOnly = true)
    public PageResponseDto<TripPreviewDto> getAll(Long cursorId, LocalDateTime cursorDate, int limit) {
        List<TripPreviewDto> trips = tripQueryService.findAll(cursorId, cursorDate, limit);
        return PageResponseDto.from(trips);
    }

    @Transactional(readOnly = true)
    public TripStatusResponse getOneStatus(Long tripId) {
        Trip trip = tripQueryService.findById(tripId);
        List<TripMemberDto> tripMembers = tripMemberQueryService.findMembersByTrip(trip);
        return TripStatusDtoMapper.toDto(trip, tripMembers);
    }

    @Transactional(readOnly = true)
    public TripGetOneResponse getOne(Long memberId, Long tripId) {
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

    @Transactional(readOnly = true)
    public Trip findByTripTag(Long memberId){
        return tripQueryService.findByTripTag(memberId);
    }

    private Boolean isJoined(Long tripId, Long memberId) {
        return tripMemberQueryService.existsByTripIdAndMemberId(tripId, memberId);
    }

}
