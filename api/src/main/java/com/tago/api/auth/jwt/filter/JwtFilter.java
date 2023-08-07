package com.tago.api.auth.jwt.filter;

import com.tago.api.auth.jwt.JwtProvider;
import com.tago.api.auth.jwt.exception.AuthenticateException;
import com.tago.api.auth.jwt.handler.JwtAuthenticationEntryPoint;
import com.tago.domain.common.error.BaseBusinessException;
import com.tago.domain.common.error.ErrorCode;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final JwtProvider jwtProvider;
    private final AuthorizationHeaderTokenResolver authorizationHeaderTokenResolver;
    private final JwtAuthenticationEntryPoint authenticationEntryPoint;

    private static final String HEADER_NAME = "Authorization";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        try {
            String value = request.getHeader(HEADER_NAME);
            String token = authorizationHeaderTokenResolver.resolve(value);
            Authentication authentication = jwtProvider.authenticate(token);

            SecurityContextHolder.getContext()
                    .setAuthentication(authentication);
            filterChain.doFilter(request, response);
        } catch (AuthenticateException e) {
            authenticationEntryPoint.commence(request, response, e);
        } catch (IOException | ServletException e) {
            throw new BaseBusinessException(ErrorCode.DEFAULT, e);
        }
    }
}
