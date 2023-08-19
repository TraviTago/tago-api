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


    //Trip 참여시, 현재 멤버 수 증가
    public void incrementCurrentMember(Trip trip){
        trip.setCurrent_member(trip.getCurrent_member() + 1);
        tripRepository.save(trip);
    }

    public Trip getTripById(Long tripId) {
        return tripRepository.findById(tripId).orElseThrow(() -> new IllegalArgumentException("Invalid tripId"));
    }

}

