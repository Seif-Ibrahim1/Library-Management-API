package com.example.librarymanagement.Exceptions;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    // Exception handler for ResourceNotFoundException
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex) {
        // Create an ErrorResponse object with HTTP status 404 (Not Found) and the provided error message
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        // Return the error response along with HTTP status 404
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    // Exception handler for MethodArgumentNotValidException (used for request validation errors)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        // Create a map to hold the error response details
        Map<String, Object> response = new HashMap<>();
        // Add timestamp
        response.put("timestamp", LocalDateTime.now());
        // Add status code
        response.put("status", HttpStatus.BAD_REQUEST.value());
        // Add error type
        response.put("error", HttpStatus.BAD_REQUEST.getReasonPhrase());
        // Add error message containing details of validation errors
        response.put("message", ex.getBindingResult().getAllErrors().stream()
                .map(error -> ((FieldError) error).getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", ")));
        // Add request path
        response.put("path", "/api/books");
        // Return the error response with HTTP status 400 (Bad Request)
        return ResponseEntity.badRequest().body(response);
    }

}
