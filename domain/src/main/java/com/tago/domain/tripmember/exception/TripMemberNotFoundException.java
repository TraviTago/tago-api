package com.tago.domain.tripmember.exception;

import com.tago.domain.common.error.BaseBusinessException;
import com.tago.domain.common.error.ErrorCode;

public class TripMemberNotFoundException extends BaseBusinessException {

    public TripMemberNotFoundException(){super(ErrorCode.TRIPMEMBER_NOT_FOUND);}
}
