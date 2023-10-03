package com.tago.api.auth.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtTokenGenerator {
    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 30;            // 30분
    private static final long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 7;  // 7일

    private final JwtProperties jwtProperties;

    public String generateAccessToken(Long memberId) {
        long now = (new Date()).getTime();
        Date accessTokenExpiredAt = new Date(now + ACCESS_TOKEN_EXPIRE_TIME);
        return generate(memberId, accessTokenExpiredAt);
    }

    public String generateRefreshToken(Long memberId) {
        long now = (new Date()).getTime();
        Date refreshTokenExpiredAt = new Date(now + REFRESH_TOKEN_EXPIRE_TIME);
        return generate(memberId, refreshTokenExpiredAt);
    }

    private String generate(Long userId, Date expiredAt) {
        Map<String, Object> claims = createClaims(userId);
        Key key = jwtProperties.getKey();

        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(expiredAt)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    private Map<String, Object> createClaims(Long userId) {
        return Map.of("userId", userId);
    }
}
