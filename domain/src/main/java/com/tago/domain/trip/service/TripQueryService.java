package com.tago.domain.trip.service;

import com.tago.domain.trip.domain.Trip;
import com.tago.domain.trip.exception.TripNotFoundException;
import com.tago.domain.trip.dto.TripPreviewDto;
import com.tago.domain.trip.mapper.TripDtoMapper;
import com.tago.domain.trip.repository.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TripQueryService {

    private final TripRepository tripRepository;

    public Trip findById(Long tripId) {
        return tripRepository.findById(tripId)
                .orElseThrow(TripNotFoundException::new);
    }
  
    public List<TripPreviewDto> findAll(Long cursorId, LocalDateTime cursorDate, int limit) {
        List<Trip> trips = tripRepository.findAllFetchTripPlaceAndPlace(cursorId, cursorDate, limit);
        return TripDtoMapper.toTripPreviews(trips);
    }

    public List<TripPreviewDto> searchByPlaceTitleKeyword(
            String keyword, Long cursorId, LocalDateTime cursorDate, int limit
    ) {
        List<Trip> trips = tripRepository.findByPlaceTitleKeywordContain(keyword, cursorId, cursorDate, limit);
        return TripDtoMapper.toTripPreviews(trips);
    }
}
