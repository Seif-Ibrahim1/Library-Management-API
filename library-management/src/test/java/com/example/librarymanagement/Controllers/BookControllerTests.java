package com.example.librarymanagement.Controllers;

import com.example.librarymanagement.Entities.Book;
import com.example.librarymanagement.Services.BookService;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;


@SpringBootTest
public class BookControllerTests {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    @Test
    public void testGetBooks() {
        // Mock data
        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("Book 1", "Author 1", 2022, "1234567890"));
        books.add(new Book("Book 2", "Author 2", 2023, "0987654321"));

        // Mock service method
        when(bookService.getBooks()).thenReturn(books);

        // Invoke controller method
        ResponseEntity<ArrayList<Book>> response = bookController.getBooks();

        // Check response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(books, response.getBody());
    }

    @Test
    public void testGetBookById_ValidId() {
        // Mock data
        long validId = 1L;
        Book book = new Book("Book 1", "Author 1", 2022, "1234567890");

        // Mock service method
        when(bookService.getBook(validId)).thenReturn(book);

        // Invoke controller method
        ResponseEntity<Book> response = bookController.getBook(validId);

        // Check response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(book, response.getBody());
    }

    @Test
    public void testGetBookById_InvalidId() {
        // Mock data
        long invalidId = 100L; // Assuming this ID does not exist

        // Mock service method to return null, indicating book not found
        when(bookService.getBook(invalidId)).thenReturn(null);

        // Invoke controller method
        ResponseEntity<Book> response = bookController.getBook(invalidId);

        // Check response
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testAddBook() {
        // Mock data
        Book newBook = new Book("New Book", "New Author", 2023, "9876543210");

        // Mock service method (just for verification, since addBook returns void)
        doNothing().when(bookService).addBook(newBook);

        // Invoke controller method
        ResponseEntity<Book> response = bookController.addBook(newBook);

        // Check response
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(newBook, response.getBody());

        // Verify that the bookService's addBook method was called with the newBook parameter
        verify(bookService, times(1)).addBook(newBook);
    }


    @Test
    public void testUpdateBook_ValidId() {
        // Mock data
        long validId = 1L;
        Book updatedBook = new Book("Updated Book", "Updated Author", 2023, "9876543210");

        // Mock service method
        when(bookService.updateBook(validId, updatedBook)).thenReturn(updatedBook);

        // Invoke controller method
        ResponseEntity<Book> response = bookController.updateBook(validId, updatedBook);

        // Check response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedBook, response.getBody());
    }

    @Test
    public void testUpdateBook_InvalidId() {
        // Mock data
        long invalidId = 100L; // Assuming this ID does not exist
        Book updatedBook = new Book("Updated Book", "Updated Author", 2023, "9876543210");

        // Mock service method to return null, indicating book not found
        when(bookService.updateBook(invalidId, updatedBook)).thenReturn(null);

        // Invoke controller method
        ResponseEntity<Book> response = bookController.updateBook(invalidId, updatedBook);

        // Check response
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

}
