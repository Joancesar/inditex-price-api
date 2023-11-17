package com.inditex.inditexpriceapi.shared.model;

import com.inditex.inditexpriceapi.shared.model.enu.ErrorCode;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ErrorDTO(
        HttpStatus status,
        int statusCode,
        ErrorCode errorCode,
        String message,
        String debugMessage,
        LocalDateTime timestamp,
        String path) {

    public ErrorDTO(HttpStatus status, ErrorCode errorCode, String message, String debugMessage, String path) {
        this(status, status.value(), errorCode, message, debugMessage, LocalDateTime.now(), path);
    }
}