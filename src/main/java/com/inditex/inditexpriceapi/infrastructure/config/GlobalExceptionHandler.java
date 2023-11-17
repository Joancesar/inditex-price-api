package com.inditex.inditexpriceapi.infrastructure.config;

import com.inditex.inditexpriceapi.domain.exception.PriceNotFoundException;
import com.inditex.inditexpriceapi.shared.model.ErrorDTO;
import com.inditex.inditexpriceapi.shared.model.enu.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PriceNotFoundException.class)
    public ResponseEntity<ErrorDTO> handlePriceNotFoundException(PriceNotFoundException ex, HttpServletRequest request) {
        ErrorDTO errorDTO = new ErrorDTO(
                HttpStatus.NOT_FOUND,
                ErrorCode.RESOURCE_NOT_FOUND,
                ErrorCode.RESOURCE_NOT_FOUND.getMessage(),
                ex.getMessage(),
                request.getRequestURI()
        );
        return new ResponseEntity<>(errorDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleGeneralException(Exception ex, HttpServletRequest request) {

        ErrorDTO errorDTO = new ErrorDTO(
                HttpStatus.INTERNAL_SERVER_ERROR,
                ErrorCode.INTERNAL_SERVER_ERROR,
                ErrorCode.INTERNAL_SERVER_ERROR.getMessage(),
                ex.getMessage(),
                request.getRequestURI()
        );
        return new ResponseEntity<>(errorDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}