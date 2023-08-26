package com.tago.domain.member.exception;
import com.tago.domain.common.error.BaseBusinessException;
import com.tago.domain.common.error.ErrorCode;
public class MaxMemberLimitException extends BaseBusinessException {

    public MaxMemberLimitException(){
        super(ErrorCode.MAX_MEMBER_LIMIT_REACHED);
    }
}