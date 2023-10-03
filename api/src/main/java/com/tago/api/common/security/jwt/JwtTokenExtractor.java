package com.tago.api.common.security.jwt;

import com.tago.api.common.security.jwt.dto.JwtTokenInfo;
import com.tago.api.common.security.jwt.exception.AuthenticateException;
import com.tago.api.common.security.jwt.exception.ErrorMessages;
import com.tago.domain.member.domain.vo.Role;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.Key;

@Component
@RequiredArgsConstructor
public class JwtTokenExtractor {
    private final JwtProperties jwtProperties;

    public JwtTokenInfo extract(String token) {
        Claims claims = parseClaims(token);

        return new JwtTokenInfo(
                claims.get("id", Long.class),
                claims.get("role", String.class)
        );
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
