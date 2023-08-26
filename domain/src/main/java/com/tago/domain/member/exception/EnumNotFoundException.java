package com.tago.domain.member.exception;

import com.tago.domain.common.error.BaseBusinessException;
import com.tago.domain.common.error.ErrorCode;

public class EnumNotFoundException extends BaseBusinessException {
    public EnumNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
