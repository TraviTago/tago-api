package com.tago.domain.common.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    // Member
    MEMBER_NOT_FOUND(400, "M001", "존재하지 않는 멤버입니다")
    ;

    private int status;
    private String code;
    private String message;

    private ErrorCode(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
