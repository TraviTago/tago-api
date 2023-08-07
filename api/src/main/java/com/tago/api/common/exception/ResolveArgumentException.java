package com.tago.api.common.exception;

import com.tago.domain.common.error.BaseBusinessException;
import com.tago.domain.common.error.ErrorCode;

public class ResolveArgumentException extends BaseBusinessException {
    public ResolveArgumentException(ErrorCode errorCode) {
        super(errorCode);
    }
}
