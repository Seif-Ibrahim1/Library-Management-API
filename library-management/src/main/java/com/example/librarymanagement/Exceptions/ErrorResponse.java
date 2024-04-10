package com.example.librarymanagement.Exceptions;

import org.springframework.http.HttpStatus;

/**
 * Represents an error response containing the HTTP status code and error message.
 */
public class ErrorResponse {
    private HttpStatus status; // The HTTP status code of the error response
    private String message; // The error message explaining the issue

    /**
     * Constructs an ErrorResponse with the specified HTTP status and message.
     * @param status The HTTP status code of the error response.
     * @param message The error message explaining the issue.
     */
    public ErrorResponse(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    /**
     * Retrieves the HTTP status code of the error response.
     * @return The HTTP status code.
     */
    public HttpStatus getStatus() {
        return status;
    }

    /**
     * Sets the HTTP status code of the error response.
     * @param status The HTTP status code to set.
     */
    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    /**
     * Retrieves the error message explaining the issue.
     * @return The error message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the error message explaining the issue.
     * @param message The error message to set.
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
