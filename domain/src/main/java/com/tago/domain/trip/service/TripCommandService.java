package com.tago.domain.trip.service;

import com.tago.domain.trip.domain.Trip;
import com.tago.domain.trip.domain.TripMember;
import com.tago.domain.trip.repository.TripMemberRepository;
import com.tago.domain.trip.repository.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TripCommandService {

    private final TripRepository tripRepository;

    public boolean isValidTripId(Long tripId){
        return tripRepository.existsById(tripId);
    }

}

