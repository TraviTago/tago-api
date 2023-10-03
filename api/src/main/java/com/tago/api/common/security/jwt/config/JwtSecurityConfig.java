package com.tago.api.common.security.jwt.config;

import com.tago.api.common.security.jwt.JwtProvider;
import com.tago.api.common.security.jwt.filter.AuthorizationHeaderTokenResolver;
import com.tago.api.common.security.jwt.filter.JwtFilter;
import com.tago.api.common.security.jwt.handler.JwtAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
public class JwtSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    private final JwtProvider jwtProvider;
    private final AuthorizationHeaderTokenResolver authorizationHeaderTokenResolver;
    private final JwtAuthenticationEntryPoint authenticationEntryPoint;

    @Override
    public void configure(HttpSecurity http) {
        JwtFilter jwtFilter = new JwtFilter(
                jwtProvider,
                authorizationHeaderTokenResolver,
                authenticationEntryPoint
        );
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
