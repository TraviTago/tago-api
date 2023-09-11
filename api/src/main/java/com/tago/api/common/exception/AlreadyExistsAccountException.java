package com.tago.api.common.exception;

import com.tago.domain.common.error.BaseBusinessException;
import com.tago.domain.common.error.ErrorCode;

public class AlreadyExistsAccountException extends BaseBusinessException {
    public AlreadyExistsAccountException() {
        super(ErrorCode.ALREADY_EXISTS_ACCOUNT);
    }
}
