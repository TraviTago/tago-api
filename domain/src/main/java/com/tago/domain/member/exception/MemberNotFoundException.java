package com.tago.domain.member.exception;

import com.tago.domain.common.exception.BaseBusinessException;
import com.tago.domain.common.exception.ErrorCode;

public class MemberNotFoundException extends BaseBusinessException {
    public MemberNotFoundException() {
        super(ErrorCode.MEMBER_NOT_FOUND);
    }
}
