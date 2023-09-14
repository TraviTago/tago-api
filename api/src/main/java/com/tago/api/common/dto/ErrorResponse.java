package com.tago.api.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tago.domain.common.error.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ErrorResponse {
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd HH:mm:ss",
            locale = "Asia/Seoul"
    )
    private LocalDateTime timestamp;
    private String path;
    private int status;
    private String code;
    private String message;
    private String reason;

    public static ResponseEntity<ErrorResponse> toResponseEntity(ErrorCode errorCode, String path) {
        return ResponseEntity
                .status(errorCode.getStatus())
                .body(new ErrorResponse(
                        LocalDateTime.now(),
                        path,
                        errorCode.getStatus(),
                        errorCode.getCode(),
                        errorCode.name(),
                        errorCode.getMessage()
                ));
    }

    public static ResponseEntity<ErrorResponse> toResponseEntity(
            String path, int status, String message, String reason
    ) {
        return ResponseEntity
                .status(status)
                .body(new ErrorResponse(
                        LocalDateTime.now(),
                        path,
                        status,
                        ErrorCode.DEFAULT.getCode(),
                        message,
                        reason
                ));
    }
}
