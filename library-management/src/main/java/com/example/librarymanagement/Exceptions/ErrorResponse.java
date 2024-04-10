package com.example.librarymanagement.Exceptions;

import org.springframework.http.HttpStatus;

public class ErrorResponse {
    private HttpStatus status; // The HTTP status code of the error response
    private String message; // The error message explaining the issue

    // Constructor to initialize the ErrorResponse with status and message
    public ErrorResponse(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    // Getter method to retrieve the HTTP status code
    public HttpStatus getStatus() {
        return status;
    }

    // Setter method to set the HTTP status code
    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    // Getter method to retrieve the error message
    public String getMessage() {
        return message;
    }

    // Setter method to set the error message
    public void setMessage(String message) {
        this.message = message;
    }
}
