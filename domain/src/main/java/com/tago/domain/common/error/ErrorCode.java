package com.tago.domain.common.error;

import lombok.Getter;

@Getter
public enum ErrorCode {

    // AUTH
    UNAUTHORIZED(401, "AU001", "잘못된 토큰입니다."),
    INVALID_AUTHORIZATION_SCHEMA(401, "AU002", "잘못된 Authorization 스키마입니다."),
    EMPTY_AUTHORIZATION_SCHEMA(401, "AU003", "Authorization 스키마가 없습니다."),
    EMPTY_ACCESS_TOKEN(401, "AU004", "헤더에 access token 존재하지 않습니다."),
    INVALID_AUTHORIZATION_HEADER(400, "AU005", "잘돗된 형식의 Authorization 헤더입니다."),
    PRINCIPAL_TYPE_NOT_MATCH(400, "AU006", "Authorization의 principal 객체가 Long 타입이 아닙니다."),
    ALREADY_EXISTS_ACCOUNT(400, "A007", "이미 가입된 번호입니다."),

    // SMS
    SMS_BAD_REQUEST(400, "S001", "잘못된 형식의 SMS 요청입니다."),
    SMS_UNAUTHORIZED(401, "S002", "유효하지 않은 SMS 요청 헤더값입니다."),
    VERIFICATION_CODE_INVALID(400, "S003", "올바르지 않은 인증코드입니다."),
    VERIFICATION_CODE_EXPIRED(400, "S004", "인증 코드가 만료되었습니다."),

    // MEMBER
    MEMBER_NOT_FOUND(404, "M001", "존재하지 않는 멤버입니다."),
    TRIP_TYPE_NOT_FOUND(404, "M002", "존재하지 않는 TRIPTYPE 입니다."),
    FAVORITE_NOT_FOUND(404, "M003", "존재하지 않는 FAVORITE 입니다."),
    GENDER_NOT_FOUND(404, "M004", "존재하지 않는 GENDER 입니다."),
    TRIPMEMBER_NOT_FOUND(404,"M005","존재하지 않는 TRIPMEMBER 입니다."),

    // TRIP
    TRIP_NOT_FOUND(404, "T001", "존재하지 않는 여행입니다."),
    MAX_MEMBER_LIMIT_REACHED(400, "T002", "이 여행은 이미 최대 멤버 수에 도달하였습니다."),

    // PLACE
    PLACE_NOT_FOUND(404,"P001","존재하지 않은 여행지입니다."),

    // COURSE
    COURSE_WITH_MAIN_PLACE_NOT_FOUND(404, "C001", "꼭 가고싶은 여행지가 포함된 코스가 존재하지 않습니다."),
    COURSE_NOT_MATCHING(404, "C002", "조건에 맞는 코스가 존재하지 않습니다."),

    // TAG
    TAG_NOT_FOUND(404, "TA001", "존재하지 않는 태그입니다."),

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
