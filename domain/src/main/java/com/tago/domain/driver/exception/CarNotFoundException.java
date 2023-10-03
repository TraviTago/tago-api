package com.tago.domain.driver.exception;

import com.tago.domain.common.error.BaseBusinessException;
import com.tago.domain.common.error.ErrorCode;

public class CarNotFoundException extends BaseBusinessException {
    public CarNotFoundException() {
        super(ErrorCode.CAR_NOT_FOUND);
    }
}
