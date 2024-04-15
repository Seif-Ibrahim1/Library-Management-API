package com.example.librarymanagement.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.librarymanagement.Entities.Book;
import com.example.librarymanagement.Services.BookService;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.validation.Valid;
import java.util.ArrayList;

/**
 * Controller class for managing book-related endpoints.
 */
@RestController
@RequestMapping("/api/books")
@Validated
public class BookController {
    private final BookService bookService;
    
    /**
     * Constructor for BookController.
     * @param bookService The service for managing book-related operations.
     */
    
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * Handler for HTTP GET request to retrieve all books.
     * @return ResponseEntity with list of books and HTTP status OK.
     */
    @GetMapping()
    public ResponseEntity<ArrayList<Book>> getBooks() {
        System.out.println("Get all books");
        // Retrieve list of books from BookService
        ArrayList<Book> books = null;
        try {
            books = bookService.getBooks();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        
        // Return list of books with HTTP status OK
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    /**
     * Handler for HTTP GET request to retrieve a specific book by ID.
     * @param id The ID of the book to retrieve.
     * @return ResponseEntity with the retrieved book and HTTP status OK, or NOT_FOUND if book is not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable Long id) {
        // Retrieve the book with the given ID from BookService
        Book book = bookService.getBook(id);
        // Check if book is found
        if (book != null) {
            // Return book with HTTP status OK
            return new ResponseEntity<>(book, HttpStatus.OK);
        } else {
            // Return HTTP status NOT_FOUND if book is not found
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Handler for HTTP POST request to add a new book.
     * @param book The book object to add.
     * @return ResponseEntity with the added book and HTTP status CREATED.
     */
    @PostMapping()
    public ResponseEntity<Book> addBook(@Valid @RequestBody Book book) {
        // Add the new book using BookService
        bookService.addBook(book);
        // Return the added book with HTTP status CREATED
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    /**
     * Handler for HTTP PUT request to update an existing book.
     * @param id The ID of the book to update.
     * @param book The updated book object.
     * @return ResponseEntity with the updated book and HTTP status OK, or NOT_FOUND if book is not found.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
        // Update the book with the given ID using BookService
        Book updatedBook = bookService.updateBook(id, book);
        // Check if book is updated successfully
        if (updatedBook != null) {
            // Return the updated book with HTTP status OK
            return new ResponseEntity<>(updatedBook, HttpStatus.OK);
        } else {
            // Return HTTP status NOT_FOUND if book is not found
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Handler for HTTP DELETE request to delete a book by ID.
     * @param id The ID of the book to delete.
     * @return ResponseEntity with HTTP status NO_CONTENT indicating successful deletion.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        // Delete the book with the given ID using BookService
        bookService.deleteBook(id);
        // Return HTTP status NO_CONTENT indicating successful deletion
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleSecurityException(Exception exception) {
        ProblemDetail errorDetail = null;
        System.out.println("Error: " + exception.getMessage());

        if (exception instanceof BadCredentialsException) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(401), exception.getMessage());
            errorDetail.setProperty("description", "The username or password is incorrect");
            System.out.println("Error: " + exception.getMessage());

            return errorDetail;
        }

        if (exception instanceof AccountStatusException) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), exception.getMessage());
            errorDetail.setProperty("description", "The account is locked");
            System.out.println("Error: " + exception.getMessage());
        }

        if (exception instanceof AccessDeniedException) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), exception.getMessage());
            errorDetail.setProperty("description", "You are not authorized to access this resource");
            System.out.println("Error: " + exception.getMessage());
        }

        if (exception instanceof SignatureException) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), exception.getMessage());
            errorDetail.setProperty("description", "The JWT signature is invalid");
            System.out.println("Error: " + exception.getMessage());
        }

        if (exception instanceof ExpiredJwtException) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), exception.getMessage());
            errorDetail.setProperty("description", "The JWT token has expired");
            System.out.println("Error: " + exception.getMessage());
        }

        if (errorDetail == null) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(500), exception.getMessage());
            errorDetail.setProperty("description", "Unknown internal server error.");
            System.out.println("Error: " + exception.getMessage());
        }

        return errorDetail;
    }
}
