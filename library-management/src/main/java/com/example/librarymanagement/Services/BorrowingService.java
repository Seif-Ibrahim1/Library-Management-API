package com.example.librarymanagement.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.librarymanagement.Entities.Book;
import com.example.librarymanagement.Entities.Borrowing;
import com.example.librarymanagement.Entities.Patron;
import com.example.librarymanagement.Exceptions.ResourceNotFoundException;
import com.example.librarymanagement.Repositories.BorrowingRepository;
import com.example.librarymanagement.Repositories.BookRepository;
import com.example.librarymanagement.Repositories.PatronRepository;

import java.time.LocalDate;

/**
 * Service class for managing borrowing operations.
 */
@Service
public class BorrowingService {
    @Autowired
    private final BorrowingRepository borrowingRepository;
    @Autowired
    private final BookRepository bookRepository;
    @Autowired
    private final PatronRepository patronRepository;

    /**
     * Constructor for BorrowingService.
     * @param borrowingRepository The repository for managing borrowing entities.
     * @param bookRepository The repository for managing book entities.
     * @param patronRepository The repository for managing patron entities.
     */
    public BorrowingService(BorrowingRepository borrowingRepository, BookRepository bookRepository, PatronRepository patronRepository) {
        this.borrowingRepository = borrowingRepository;
        this.bookRepository = bookRepository;
        this.patronRepository = patronRepository;
    }

    /**
     * Borrow a book for a patron.
     * @param bookId The ID of the book to borrow.
     * @param patronId The ID of the patron borrowing the book.
     */
    @Transactional // Apply @Transactional annotation to the method
    public void borrowBook(Long bookId, Long patronId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + bookId));
        Patron patron = patronRepository.findById(patronId)
                .orElseThrow(() -> new ResourceNotFoundException("Patron not found with id: " + patronId));

        Borrowing borrowing = new Borrowing();
        borrowing.setBook(book);
        borrowing.setPatron(patron);
        borrowing.setBorrowDate(LocalDate.now());
        borrowingRepository.save(borrowing);
    }

    /**
     * Return a borrowed book by a patron.
     * @param bookId The ID of the book to return.
     * @param patronId The ID of the patron returning the book.
     */
    @Transactional // Apply @Transactional annotation to the method
    public void returnBook(Long bookId, Long patronId) {
        Borrowing borrowing = borrowingRepository.findByBookIdAndPatronId(bookId, patronId);
        if (borrowing != null) {
            borrowing.setReturnDate(LocalDate.now());
            borrowingRepository.save(borrowing);
        } else {
            throw new ResourceNotFoundException("Borrowing not found for bookId: " + bookId + " and patronId: " + patronId);
        }
    }
}
