package com.tago.api.auth.jwt.exception;

import org.springframework.security.core.AuthenticationException;

public class AuthenticateException extends AuthenticationException {
    public AuthenticateException(String message) {
        super(message);
    }
}
