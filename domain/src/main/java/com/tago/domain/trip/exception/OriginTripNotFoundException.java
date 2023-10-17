package com.tago.domain.trip.exception;

import com.tago.domain.common.error.BaseBusinessException;
import com.tago.domain.common.error.ErrorCode;

public class OriginTripNotFoundException extends BaseBusinessException {
    public OriginTripNotFoundException() {
        super(ErrorCode.ORIGIN_TRIP_NOT_FOUND);
    }
}
