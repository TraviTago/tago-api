package com.tago.api.common.security.jwt.exception;

import org.springframework.security.core.AuthenticationException;

public class AuthenticateException extends AuthenticationException {
    public AuthenticateException(String message) {
        super(message);
    }
}
