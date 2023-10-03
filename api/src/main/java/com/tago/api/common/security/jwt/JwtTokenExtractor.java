package com.tago.api.auth.jwt;

import com.tago.api.auth.jwt.exception.AuthenticateException;
import com.tago.api.auth.jwt.exception.ErrorMessages;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.Key;

@Component
@RequiredArgsConstructor
public class JwtTokenExtractor {
    private final JwtProperties jwtProperties;

    public Long extract(String token) {
        Claims claims = parseClaims(token);
        return claims.get("userId", Long.class);
    }

    private Claims parseClaims(String token) {
        Key key = jwtProperties.getKey();

        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException expiredJwtException) {
            throw new AuthenticateException(ErrorMessages.EXPIRED_TOKEN);
        } catch (UnsupportedJwtException unsupportedJwtException) {
            throw new AuthenticateException(ErrorMessages.UN_SUPPORTED_TOKEN);
        } catch (MalformedJwtException malformedJwtException) {
            throw new AuthenticateException(ErrorMessages.INVALID_TOKEN);
        } catch (IllegalArgumentException illegalArgumentException) {
            throw new AuthenticateException(ErrorMessages.INVALID_TOKEN_HEADER);
        }
    }
}
