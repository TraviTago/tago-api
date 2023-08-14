package com.tago.domain.trip.service;

import com.tago.domain.trip.repository.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TripQueryService {

    private final TripRepository tripRepository;
}
