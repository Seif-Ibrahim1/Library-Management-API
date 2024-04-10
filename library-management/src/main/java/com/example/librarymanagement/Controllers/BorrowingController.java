package com.example.librarymanagement.Controllers;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.librarymanagement.Services.BorrowingService;

/**
 * Controller class for managing borrowing operations.
 */
@RestController
@RequestMapping("/api")
public class BorrowingController {
    private final BorrowingService borrowingService;
    
    /**
     * Constructor for BorrowingController.
     * @param borrowingService The service for managing borrowing operations.
     */
    public BorrowingController(BorrowingService borrowingService) {
        this.borrowingService = borrowingService;
    }

    /**
     * Endpoint for a patron to borrow a book.
     * @param bookId The ID of the book to be borrowed.
     * @param patronId The ID of the patron borrowing the book.
     * @return ResponseEntity with a JSON object containing the message indicating successful borrowing.
     */
    @PostMapping("/borrow/{bookId}/patron/{patronId}")
    public ResponseEntity<Object> borrowBook(@PathVariable Long bookId, @PathVariable Long patronId) {
        borrowingService.borrowBook(bookId, patronId);
        // Create a JSON object with the message
        return new ResponseEntity<>(Map.of("message", "Book borrowed successfully"), HttpStatus.CREATED);
    }

    /**
     * Endpoint for a patron to return a borrowed book.
     * @param bookId The ID of the book to be returned.
     * @param patronId The ID of the patron returning the book.
     * @return ResponseEntity with a JSON object containing the message indicating successful return.
     */
    @PutMapping("/return/{bookId}/patron/{patronId}")
    public ResponseEntity<Object> returnBook(@PathVariable Long bookId, @PathVariable Long patronId) {
        borrowingService.returnBook(bookId, patronId);
        // Create a JSON object with the message
        return new ResponseEntity<>(Map.of("message", "Book returned successfully"), HttpStatus.OK);
    }
}
