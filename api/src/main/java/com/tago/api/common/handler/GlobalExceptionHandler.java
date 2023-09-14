package com.tago.api.common.handler;

import com.tago.domain.common.error.BaseBusinessException;
import com.tago.api.common.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Optional;

@RestControllerAdvice
public class GlobalExceptionHandler {

    public static String METHOD_ARGUMENT_NOT_VALID_DEFAULT_MESSAGE = "잘못된 형식의 입력 값 입니다.";
    public static String INTERNAL_EXCEPTION_DEFAULT_MESSAGE = "서버 내부 오류입니다.";

    @ExceptionHandler(value = BaseBusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(
            BaseBusinessException ex,
            HttpServletRequest httpServletRequest
    ) {
        return ErrorResponse.toResponseEntity(
                ex.getErrorCode(),
                httpServletRequest.getRequestURI()
        );
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpStatus status,
            HttpServletRequest request
    ) {
        String message = Optional.ofNullable(ex.getBindingResult().getFieldError().getDefaultMessage())
                .orElseGet(() -> METHOD_ARGUMENT_NOT_VALID_DEFAULT_MESSAGE);

        return ErrorResponse.toResponseEntity(
                request.getRequestURI(),
                status.value(),
                status.name(),
                message
        );
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponse> handleInternalException(
            MethodArgumentNotValidException ex,
            HttpStatus status,
            HttpServletRequest request
    ) {
        return ErrorResponse.toResponseEntity(
                request.getRequestURI(),
                status.value(),
                status.name(),
                INTERNAL_EXCEPTION_DEFAULT_MESSAGE
        );
    }
}
