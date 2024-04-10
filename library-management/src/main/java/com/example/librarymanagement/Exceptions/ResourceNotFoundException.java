package com.example.librarymanagement.Exceptions;

public class ResourceNotFoundException extends RuntimeException {
    
    // Constructor to initialize the exception with a custom error message
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
