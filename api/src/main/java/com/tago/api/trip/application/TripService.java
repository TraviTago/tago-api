package com.tago.api.trip.application;

import com.tago.api.common.dto.PageResponseDto;
import com.tago.api.common.mapper.TripStatusDtoMapper;
import com.tago.api.trip.dto.response.TripStatusResponse;
import com.tago.domain.trip.domain.Trip;
import com.tago.domain.trip.dto.TripPreviewDto;
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
public class TripService {

    private final TripQueryService tripQueryService;
    private final TripMemberQueryService tripMemberQueryService;

    @Transactional(readOnly = true)
    public PageResponseDto<TripPreviewDto> getAll(Long cursorId, LocalDateTime cursorDate, int limit) {
        List<TripPreviewDto> trips = tripQueryService.findAll(cursorId, cursorDate, limit);
        return PageResponseDto.from(trips);
    }

    @Transactional(readOnly = true)
    public TripStatusResponse getTripStatus(Long tripId) {
        Trip trip = tripQueryService.findById(tripId);
        List<TripMemberDto> tripMembers = tripMemberQueryService.findMembersByTrip(trip);

        return TripStatusDtoMapper.toDto(trip, tripMembers);
    }
}
