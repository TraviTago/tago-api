package com.tago.domain.driver.exception;

import com.tago.domain.common.error.BaseBusinessException;
import com.tago.domain.common.error.ErrorCode;

public class DriverNotFoundException extends BaseBusinessException {
    public DriverNotFoundException() {
        super(ErrorCode.DRIVER_NOT_FOUND);
    }
}
