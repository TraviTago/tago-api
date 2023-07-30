package com.tago.domain.auth.jwt;

import com.tago.domain.auth.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenGenerator {
    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 30;            // 30분
    private static final long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 7;  // 7일

    private final JwtTokenProvider jwtTokenProvider;

    public String generateAccessToken(Long memberId) {
        long now = (new Date()).getTime();
        Date accessTokenExpiredAt = new Date(now + ACCESS_TOKEN_EXPIRE_TIME);
        return generateToken(memberId, accessTokenExpiredAt);
    }

    public String generateRefreshToken(Long memberId) {
        long now = (new Date()).getTime();
        Date refreshTokenExpiredAt = new Date(now + REFRESH_TOKEN_EXPIRE_TIME);
        return generateToken(memberId, refreshTokenExpiredAt);
    }

    private String generateToken(Long memberId, Date expiredAt) {
        String subject = memberId.toString();
        return jwtTokenProvider.generate(subject, expiredAt);
    }

    public Long extractMemberId(String accessToken) {
        return Long.valueOf(jwtTokenProvider.extractSubject(accessToken));
    }
}
