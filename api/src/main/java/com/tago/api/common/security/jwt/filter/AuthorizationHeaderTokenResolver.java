package com.tago.api.common.security.jwt.filter;

import com.tago.api.common.security.jwt.exception.AuthenticateException;
import com.tago.api.common.security.jwt.exception.ErrorMessages;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthorizationHeaderTokenResolver {
    private static final String AUTHORIZATION_SCHEMA = "Bearer";

    public String resolve(String value) {
        if (value == null) throw new AuthenticateException("Authorization 헤더값이 존재하지 않습니다.");
        String[] values = value.split(" ");
        validate(values);
        return values[1];
    }

    private void validate(String[] values) {
        if (values.length != 2) {
            throw new AuthenticateException(ErrorMessages.INVALID_TOKEN_HEADER);
        }
        validateSchema(values[0]);
        validateToken(values[1]);
    }
    public void validateSchema(String schema) {
        Optional.ofNullable(schema)
                .orElseThrow(() -> new AuthenticateException(ErrorMessages.EMPTY_AUTHORIZATION_SCHEMA));
        if (!schema.equals(AUTHORIZATION_SCHEMA)) {
            throw new AuthenticateException(ErrorMessages.INVALID_AUTHORIZATION_SCHEMA);
        }
    }

    public void validateToken(String token) {
        Optional.ofNullable(token)
                .orElseThrow(() -> new AuthenticateException(ErrorMessages.EMPTY_AUTHORIZATION_TOKEN));
    }
}
