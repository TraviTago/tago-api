package com.tago.domain.driver.service.factory.dispatch;

import com.tago.domain.driver.domain.Driver;
import com.tago.domain.trip.domain.Trip;

public interface DispatchService {
    String getState();
    void dispatch(Trip trip, Driver driver);
}
