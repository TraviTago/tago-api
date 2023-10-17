package com.tago.domain.trip.handler;

import com.tago.domain.trip.domain.OriginTrip;
import com.tago.domain.trip.exception.OriginTripNotFoundException;
import com.tago.domain.trip.repository.OriginTripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OriginTripQueryService {
    private final OriginTripRepository originTripRepository;

    public OriginTrip findByName(String name) {
        return originTripRepository.findByName(name)
                .orElseThrow(OriginTripNotFoundException::new);
    }

    public List<OriginTrip> findAll() {
        return originTripRepository.findAll();
    }
}
