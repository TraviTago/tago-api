package com.tago.domain.driver.exception;

import com.tago.domain.common.error.BaseBusinessException;
import com.tago.domain.common.error.ErrorCode;

public class AlreadyExistsDispatchException extends BaseBusinessException {
    public AlreadyExistsDispatchException() {
        super(ErrorCode.ALREADY_EXISTS_DISPATCH);
    }
}
