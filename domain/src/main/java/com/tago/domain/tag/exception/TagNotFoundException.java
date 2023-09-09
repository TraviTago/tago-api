package com.tago.domain.tag.exception;

import com.tago.domain.common.error.BaseBusinessException;
import com.tago.domain.common.error.ErrorCode;

public class TagNotFoundException extends BaseBusinessException {
    public TagNotFoundException() {
        super(ErrorCode.TAG_NOT_FOUND);
    }
}
