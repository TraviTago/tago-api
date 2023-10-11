package com.tago.api.common.exception;

import com.tago.domain.common.error.BaseBusinessException;
import com.tago.domain.common.error.ErrorCode;

public class FailedSendFCMException extends BaseBusinessException {

    public FailedSendFCMException(Exception e) {
        super(ErrorCode.FAILED_SEND_FCM, e);
    }
}
