package com.tago.api.common.exception;

import com.tago.domain.common.error.BaseBusinessException;
import com.tago.domain.common.error.ErrorCode;

public class AlreadyExistsTripMemberException extends BaseBusinessException {
    public AlreadyExistsTripMemberException() {
        super(ErrorCode.ALREADY_JOINED_TRIP);
    }
}
