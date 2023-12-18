package com.inditex.inditexpriceapi.infrastructure.exception;

public class InvalidFormatException extends RuntimeException {

    public InvalidFormatException(String message, Exception ex) {
        super(message, ex);
    }
}
