package com.example.librarymanagement.Exceptions;

/**
 * Exception class for resource not found scenarios.
 */
public class ResourceNotFoundException extends RuntimeException {
    
    /**
     * Constructor to initialize the exception with a custom error message.
     * @param message The error message explaining the resource not found issue.
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
