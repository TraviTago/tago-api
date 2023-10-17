package com.tago.api.trip.scheduler.job.impl;

import com.tago.api.trip.scheduler.job.TripJob;
import com.tago.api.trip.scheduler.job.TripJobType;
import com.tago.domain.trip.service.OriginTripCreateService;
import com.tago.domain.trip.service.TripCreateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OriginTripCreateJob implements TripJob {

    private final OriginTripCreateService originTripCreateService;

    @Override
    public TripJobType getType() {
        return TripJobType.CREATE_ORIGIN_TRIP;
    }

    @Override
    public void run() {
        originTripCreateService.create();
    }
}
