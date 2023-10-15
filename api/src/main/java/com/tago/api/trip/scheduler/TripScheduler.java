package com.tago.api.trip.scheduler;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@EnableScheduling
@Component
public class TripScheduler {

    @Scheduled(cron = "0 0 0 ? * MON *")
    public void run() {

    }
}
