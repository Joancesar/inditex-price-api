package com.inditex.inditexpriceapi.shared.model.enu;

import lombok.Getter;

@Getter
public enum ErrorCode {

    RESOURCE_NOT_FOUND("Resource not found with requested criteria"),
    INTERNAL_SERVER_ERROR("Unexpected Error");

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

}