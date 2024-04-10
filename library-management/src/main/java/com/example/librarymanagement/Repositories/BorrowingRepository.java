package com.example.librarymanagement.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.librarymanagement.Entities.Borrowing;

/**
 * Repository interface for managing Borrowing entities.
 */
@Repository
public interface BorrowingRepository extends JpaRepository<Borrowing, Long> {
    
    /**
     * Finds a borrowing record by the ID of the book and the ID of the patron.
     * @param bookId The ID of the book.
     * @param patronId The ID of the patron.
     * @return The borrowing record associated with the specified book ID and patron ID.
     */
    Borrowing findByBookIdAndPatronId(Long bookId, Long patronId);
}
