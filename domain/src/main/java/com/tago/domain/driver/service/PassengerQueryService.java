package com.tago.domain.driver.service;

import com.tago.domain.driver.dto.PassengerDto;
import com.tago.domain.driver.repository.PassengerRepository;
import com.tago.domain.trip.domain.Trip;
import jdk.jfr.Frequency;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PassengerQueryService {

    private final PassengerRepository passengerRepository;

    public List<PassengerDto> findPassengerByTrip(Trip trip){
        return passengerRepository.findPassengerByTrip(trip);
    }

}
