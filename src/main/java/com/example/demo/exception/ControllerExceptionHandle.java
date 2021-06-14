package com.example.demo.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * ControllerExceptionHandle
 * Version 1.0
 * Date: 07-06-2021
 * Copyright
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * 07-06-2021           DUYHV9            Create
 */
@ControllerAdvice
public class ControllerExceptionHandle {
    /**
     * Handle Resource Not Found Exception
     *
     * @param ex      ResourceNotFoundException
     * @param request WebRequest
     * @return ResponseEntity<ErrorMessage>
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorMessage> resourceNotFound(ResourceNotFoundException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND.value(), new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<ErrorMessage>(message, HttpStatus.NOT_FOUND);
    }

    /**
     * Update user by id
     *
     * @param ex      Exception
     * @param request WebRequest
     * @return ResponseEntity<ErrorMessage>
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> globalExceptionHandler(Exception ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<ErrorMessage>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
