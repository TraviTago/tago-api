package com.tago.api.trip.scheduler.job;

public interface TripJob {
    TripJobType getType();
    void run();
}
