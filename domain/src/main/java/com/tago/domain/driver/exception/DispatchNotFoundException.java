package com.tago.domain.driver.exception;

import com.tago.domain.common.error.BaseBusinessException;
import com.tago.domain.common.error.ErrorCode;

public class DispatchNotFoundException extends BaseBusinessException {
    public DispatchNotFoundException() {
        super(ErrorCode.DISPATCH_NOT_FOUND);
    }
}
