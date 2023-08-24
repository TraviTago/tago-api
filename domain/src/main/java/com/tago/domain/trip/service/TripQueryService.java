package com.tago.domain.trip.service;

import com.tago.domain.member.domain.Member;
import com.tago.domain.member.domain.vo.Gender;
import com.tago.domain.trip.domain.Trip;
import com.tago.domain.trip.domain.TripMember;
import com.tago.domain.trip.exception.TripNotFoundException;
import com.tago.domain.trip.dto.TripPreviewDto;
import com.tago.domain.trip.mapper.TripDtoMapper;
import com.tago.domain.trip.repository.TripMemberRepository;
import com.tago.domain.trip.repository.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TripQueryService {

    private final TripRepository tripRepository;
    private final TripMemberRepository tripMemberRepository;

    public Trip findByID(Long tripId) {
        return tripRepository.findById(tripId)
                .orElseThrow(TripNotFoundException::new);
    }
  
    public List<TripPreviewDto> findAll(Long cursorId, LocalDateTime cursorDate, int limit) {
        List<Trip> trips = tripRepository.findAllFetchTripPlaceAndPlace(cursorId, cursorDate, limit);
        return TripDtoMapper.toTripPreviews(trips);
    }

}
