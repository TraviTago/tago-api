package com.tago.api.common.security.jwt.exception;

public class ErrorMessages {
    // JwtTokenExtractor
    public static final String EXPIRED_TOKEN = "만료된 JWT 토큰입니다.";
    public static final String UN_SUPPORTED_TOKEN = "지원되지 않는 형식의 JWT 토큰입니다.";
    public static final String INVALID_TOKEN = "잘못된 형식의 JWT 토큰입니다.";
    public static final String INVALID_TOKEN_HEADER = "잘못된 형식의 JWT 헤더값입니다.";

    // AuthorizationHeaderTokenResolver
    public static final String EMPTY_AUTHORIZATION_SCHEMA = "스키마가 존재하지 않습니다.";
    public static final String INVALID_AUTHORIZATION_SCHEMA = "잘못된 형식의 스키마입니다.";
    public static final String EMPTY_AUTHORIZATION_TOKEN = "토큰이 존재하지 않습니다.";

}
