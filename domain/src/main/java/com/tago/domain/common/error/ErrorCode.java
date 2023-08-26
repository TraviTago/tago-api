package com.tago.domain.common.error;

import lombok.Getter;

@Getter
public enum ErrorCode {

    // Auth
    UNAUTHORIZED(401, "AU001", "잘못된 토큰입니다."),
    INVALID_AUTHORIZATION_SCHEMA(401, "AU002", "잘못된 Authorization 스키마입니다."),
    EMPTY_AUTHORIZATION_SCHEMA(401, "AU003", "Authorization 스키마가 없습니다."),
    EMPTY_ACCESS_TOKEN(401, "AU004", "헤더에 access token 존재하지 않습니다."),
    INVALID_AUTHORIZATION_HEADER(400, "AU005", "잘돗된 형식의 Authorization 헤더입니다."),
    PRINCIPAL_TYPE_NOT_MATCH(400, "AU006", "Authorization의 principal 객체가 Long 타입이 아닙니다."),

    // Member
    MEMBER_NOT_FOUND(400, "M001", "존재하지 않는 멤버입니다"),

    //Trip
    TRIP_NOT_FOUND(400, "T001", "존재하지 않는 여행입니다."),
    MAX_MEMBER_LIMIT_REACHED(400, "T002", "이 여행은 이미 최대 멤버 수에 도달하였습니다."),

    //Place
    PLACE_NOT_FOUND(400,"P001","존재하지 않은 여행지입니다."),


    // ETC
    DEFAULT(500, "-1", "INTERNAL_SERVER_ERROR")
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
