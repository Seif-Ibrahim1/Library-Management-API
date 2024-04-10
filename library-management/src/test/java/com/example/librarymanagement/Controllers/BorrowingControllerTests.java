package com.example.librarymanagement.Controllers;

import com.example.librarymanagement.Services.BorrowingService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class BorrowingControllerTests {

    @Mock
    private BorrowingService borrowingService;

    @InjectMocks
    private BorrowingController borrowingController;

    @Test
    public void testBorrowBook() {
        // Mock data
        Long bookId = 1L;
        Long patronId = 1L;

        // Invoke controller method
        ResponseEntity<Object> response = borrowingController.borrowBook(bookId, patronId);

        // Verify service method was called with correct parameters
        verify(borrowingService).borrowBook(bookId, patronId);

        // Check response
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(Map.of("message", "Book borrowed successfully"), response.getBody());
    }

    @Test
    public void testReturnBook() {
        // Mock data
        Long bookId = 1L;
        Long patronId = 1L;

        // Invoke controller method
        ResponseEntity<Object> response = borrowingController.returnBook(bookId, patronId);

        // Verify service method was called with correct parameters
        verify(borrowingService).returnBook(bookId, patronId);

        // Check response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Map.of("message", "Book returned successfully"), response.getBody());
    }
}
