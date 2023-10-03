package com.tago.api.common.security.jwt.exception;

import com.tago.domain.common.error.BaseBusinessException;
import com.tago.domain.common.error.ErrorCode;

public class InvalidAuthorityException extends BaseBusinessException {
    public InvalidAuthorityException() {
        super(ErrorCode.INVALID_AUTHORITY);
    }
}
