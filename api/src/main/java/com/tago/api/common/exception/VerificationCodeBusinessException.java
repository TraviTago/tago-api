package com.tago.api.common.exception;

import com.tago.domain.common.error.BaseBusinessException;
import com.tago.domain.common.error.ErrorCode;

public class VerificationCodeBusinessException extends BaseBusinessException {
    public VerificationCodeBusinessException(ErrorCode errorCode) {
        super(errorCode);
    }
}
