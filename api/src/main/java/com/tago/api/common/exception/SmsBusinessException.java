package com.tago.api.common.exception;

import com.tago.domain.common.error.BaseBusinessException;
import com.tago.domain.common.error.ErrorCode;

public class SmsBusinessException extends BaseBusinessException {
    public SmsBusinessException(ErrorCode errorCode) {
        super(errorCode);
    }
}
