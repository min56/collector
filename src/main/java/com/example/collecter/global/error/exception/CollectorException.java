package com.example.collecter.global.error.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CollectorException extends RuntimeException{

    private final ErrorCode errorCode;
}
