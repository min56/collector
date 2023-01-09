package com.example.collecter.global.error;

import com.example.collecter.global.error.exception.CollectorException;
import com.example.collecter.global.error.exception.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CollectorException.class)
    public ResponseEntity<ErrorResponse> handlingCollectorException(CollectorException e) {
        ErrorCode code = e.getErrorCode();

        return new ResponseEntity<>(
                new ErrorResponse(code.getStatus(), code.getMessage()),
                HttpStatus.valueOf(code.getStatus()));
    }
}
