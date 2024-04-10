package com.example.librarymanagement.Entities;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
/**
 * Represents a borrowing record in the library management system.
 */
@Entity
@Table(name = "borrowings")
public class Borrowing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    @JsonIgnoreProperties("borrowings")
    @NotNull(message = "Book is required")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "patron_id", nullable = false)
    @JsonIgnoreProperties("borrowings")
    @NotNull(message = "Patron is required")
    private Patron patron;

    @Column(name = "borrow_date", nullable = false)
    private LocalDate borrowDate;

    @Column(name = "return_date")
    private LocalDate returnDate;
    /**
     * Default constructor.
     */
    public Borrowing() {

    }

    /**
     * Parameterized constructor.
     * @param book The book being borrowed.
     * @param patron The patron borrowing the book.
     * @param borrowDate The date when the book was borrowed.
     */
    public Borrowing(Book book, Patron patron, LocalDate borrowDate) {
        this.book = book;
        this.patron = patron;
        this.borrowDate = borrowDate;
    }

    /**
     * Retrieves the ID of the borrowing record.
     * @return The ID of the borrowing record.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the borrowing record.
     * @param id The ID to set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retrieves the book being borrowed.
     * @return The book being borrowed.
     */
    public Book getBook() {
        return book;
    }

    /**
     * Sets the book being borrowed.
     * @param book The book to set.
     */
    public void setBook(Book book) {
        this.book = book;
    }

    /**
     * Retrieves the patron borrowing the book.
     * @return The patron borrowing the book.
     */
    public Patron getPatron() {
        return patron;
    }

    /**
     * Sets the patron borrowing the book.
     * @param patron The patron to set.
     */
    public void setPatron(Patron patron) {
        this.patron = patron;
    }

    /**
     * Retrieves the date when the book was borrowed.
     * @return The date when the book was borrowed.
     */
    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    /**
     * Sets the date when the book was borrowed.
     * @param borrowDate The date to set.
     */
    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    /**
     * Retrieves the date when the book was returned.
     * @return The date when the book was returned, or null if not returned yet.
     */
    public LocalDate getReturnDate() {
        return returnDate;
    }

    /**
     * Sets the date when the book was returned.
     * @param returnDate The date to set.
     */
    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}
