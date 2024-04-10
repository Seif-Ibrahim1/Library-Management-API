package com.example.librarymanagement.Controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.librarymanagement.Entities.Book;
import com.example.librarymanagement.Services.BookService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/books")
@Validated
public class BookController {
    // Autowire BookService to handle business logic related to books
    @Autowired
    private final BookService bookService;
    
    // Constructor injection for BookService
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // Handler for HTTP GET request to retrieve all books
    @GetMapping()
    public ResponseEntity<ArrayList<Book>> getBooks() {
        // Retrieve list of books from BookService
        ArrayList<Book> books = bookService.getBooks();
        // Return list of books with HTTP status OK
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    // Handler for HTTP GET request to retrieve a specific book by ID
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

    // Handler for HTTP POST request to add a new book
    @PostMapping()
    public ResponseEntity<Book> addBook(@Valid @RequestBody Book book) {
        // Add the new book using BookService
        bookService.addBook(book);
        // Return the added book with HTTP status CREATED
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    // Handler for HTTP PUT request to update an existing book
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

    // Handler for HTTP DELETE request to delete a book by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        // Delete the book with the given ID using BookService
        bookService.deleteBook(id);
        // Return HTTP status NO_CONTENT indicating successful deletion
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
