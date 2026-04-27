package com.albertodiazsaez.similarItemApi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(HttpServerErrorException.class)
    public ResponseEntity<String> handleServerError(HttpServerErrorException ex) {

        return ResponseEntity
                .status(ex.getStatusCode())
                .body("External API server error: " + ex.getMessage());
    }
    
    @ExceptionHandler(ResourceAccessException.class)
    public ResponseEntity<String> handleConnectionError(ResourceAccessException ex) {

        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("External API unavailable: " + ex.getMessage());
    }

}
