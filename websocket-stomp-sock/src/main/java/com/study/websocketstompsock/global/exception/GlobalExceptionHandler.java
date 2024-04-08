package com.study.websocketstompsock.global.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final String INTERNAL_ERROR_MESSAGE = "서버 내부에 문제가 발생했습니다.";

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> runtimeExceptionHandler(final RuntimeException runtimeException) {
        log.warn(runtimeException.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(runtimeException.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> exceptionHandler(final Exception exception) {
        log.warn(exception.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(INTERNAL_ERROR_MESSAGE));
    }
}
