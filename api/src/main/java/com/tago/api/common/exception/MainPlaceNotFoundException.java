package com.tago.api.common.exception;

import com.tago.domain.common.error.BaseBusinessException;
import com.tago.domain.common.error.ErrorCode;

public class MainPlaceNotFoundException extends BaseBusinessException {
    public MainPlaceNotFoundException() {
        super(ErrorCode.COURSE_WITH_MAIN_PLACE_NOT_FOUND);
    }
}
