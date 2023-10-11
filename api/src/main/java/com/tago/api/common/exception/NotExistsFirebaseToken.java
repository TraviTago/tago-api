package com.tago.api.common.exception;

import com.tago.domain.common.error.BaseBusinessException;
import com.tago.domain.common.error.ErrorCode;

public class NotExistsFirebaseToken extends BaseBusinessException {
    public NotExistsFirebaseToken() {
        super(ErrorCode.NOT_EXISTS_FIREBASE_TOKEN);
    }
}
