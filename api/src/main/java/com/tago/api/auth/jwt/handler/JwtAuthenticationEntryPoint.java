package com.tago.api.auth.jwt.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tago.domain.common.exception.BaseBusinessException;
import com.tago.domain.common.exception.ErrorCode;
import com.tago.domain.common.exception.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) {
        // 유효한 자격증명을 제공하지 않고 접근하려 할때 401
        try {
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write(createWriter(authException));
        } catch (IOException e) {
            throw new BaseBusinessException(ErrorCode.DEFAULT, e);
        }
    }

    private String createWriter(AuthenticationException e) throws JsonProcessingException {
        ErrorResponse response = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.UNAUTHORIZED.value(),
                HttpStatus.UNAUTHORIZED.toString(),
                e.getMessage()
        );
        return convertJson(response);
    }

    private String convertJson(ErrorResponse errorResponse) throws JsonProcessingException {
        return objectMapper.writeValueAsString(errorResponse);
    }
}
