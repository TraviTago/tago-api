package com.tago.api.trip.scheduler;

import com.tago.api.trip.scheduler.job.TripJob;
import com.tago.api.trip.scheduler.job.TripJobFactory;
import com.tago.api.trip.scheduler.job.TripJobType;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@EnableScheduling
@Component
@RequiredArgsConstructor
public class TripScheduler {

    private final TripJobFactory tripJobFactory;

    @Scheduled(cron = "0 0 12 ? * SAT,SUN")
    public void run() {
        tripJobFactory.getInstance(TripJobType.CREATE_ORIGIN_TRIP).run();
    }
}
