package com.tago.api.trip.scheduler.job.impl;

import com.tago.api.trip.scheduler.job.TripJob;
import com.tago.api.trip.scheduler.job.TripJobType;
import com.tago.domain.trip.service.TripCreateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TripCreateJob implements TripJob {

    private final TripCreateService tripCreateService;

    @Override
    public TripJobType getType() {
        return TripJobType.CREATE_TRIP;
    }

    @Override
    public void run() {

    }
}
