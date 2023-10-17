package com.tago.api.trip.scheduler.job;

import com.tago.api.trip.scheduler.job.TripJob;
import com.tago.api.trip.scheduler.job.TripJobType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class TripJobFactory {

    private final Map<TripJobType, TripJob> jobs;

    public TripJobFactory(List<TripJob> jobs) {
        this.jobs = jobs.stream().collect(
                Collectors.toUnmodifiableMap(TripJob::getType, Function.identity())
        );
    }

    public TripJob getInstance(TripJobType type) {
        return jobs.get(type);
    }
}
