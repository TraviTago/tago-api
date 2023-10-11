package com.tago.api.common.exception;

import com.tago.domain.common.error.BaseBusinessException;
import com.tago.domain.common.error.ErrorCode;

public class NotMatchFCMActionException extends BaseBusinessException {
    public NotMatchFCMActionException() {
        super(ErrorCode.NOT_MATCH_FCM_ACTION);
    }
}
