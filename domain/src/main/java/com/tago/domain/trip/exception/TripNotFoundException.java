package com.tago.domain.trip.exception;

import com.tago.domain.common.error.BaseBusinessException;
import com.tago.domain.common.error.ErrorCode;

public class TripNotFoundException extends BaseBusinessException {
    public TripNotFoundException(){ super(ErrorCode.TRIP_NOT_FOUND);
    }

}

