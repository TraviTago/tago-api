package com.tago.api.trip.application;

import com.tago.api.trip.dto.response.TripGetAllResponse;
import com.tago.domain.place.domain.Place;
import com.tago.domain.trip.domain.Trip;
import com.tago.domain.trip.domain.TripPlace;
import com.tago.domain.trip.dto.TripPreviewDto;
import com.tago.domain.trip.mapper.TripDtoMapper;
import com.tago.domain.trip.repository.TripRepository;
import com.tago.domain.trip.service.TripQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TripService {

    private final TripQueryService tripQueryService;

    @Transactional(readOnly = true)
    public TripGetAllResponse getAll(Long cursorId, LocalDateTime cursorDate, int limit) {
        List<TripPreviewDto> trips = tripQueryService.findAll(cursorId, cursorDate, limit);

        return new TripGetAllResponse(
                hasNext(trips),
                trips
        );
    }

    private boolean hasNext(List<TripPreviewDto> trips) {
        return !trips.isEmpty();
    }
}
