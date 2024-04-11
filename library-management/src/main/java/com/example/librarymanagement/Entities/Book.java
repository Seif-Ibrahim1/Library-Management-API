package com.example.librarymanagement.Entities;

import java.util.List;

import org.hibernate.validator.constraints.ISBN;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Represents a book in the library management system.
 */
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String author;

    @NotNull
    private Integer publicationYear;

    @NotBlank
    @ISBN(type = ISBN.Type.ANY)
    private String isbn;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("book")
    private List<Borrowing> borrowings;

    /**
     * Default constructor.
     */
    public Book() {

    }

    /**
     * Parameterized constructor.
     * @param title The title of the book.
     * @param author The author of the book.
     * @param publicationYear The publication year of the book.
     * @param isbn The ISBN of the book.
     */
    public Book(String title, String author, int publicationYear, String isbn) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.isbn = isbn;
    }

    /**
     * Retrieves the ID of the book.
     * @return The ID of the book.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the book.
     * @param id The ID to set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retrieves the title of the book.
     * @return The title of the book.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the book.
     * @param title The title to set.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Retrieves the author of the book.
     * @return The author of the book.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets the author of the book.
     * @param author The author to set.
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Retrieves the publication year of the book.
     * @return The publication year of the book.
     */
    public Integer getPublicationYear() {
        return publicationYear;
    }

    /**
     * Sets the publication year of the book.
     * @param publicationYear The publication year to set.
     */
    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    /**
     * Retrieves the ISBN of the book.
     * @return The ISBN of the book.
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Sets the ISBN of the book.
     * @param isbn The ISBN to set.
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * Retrieves the list of borrowings associated with the book.
     * @return The list of borrowings associated with the book.
     */
    public List<Borrowing> getBorrowings() {
        return borrowings;
    }

    /**
     * Sets the list of borrowings associated with the book.
     * @param borrowings The list of borrowings to set.
     */
    public void setBorrowings(List<Borrowing> borrowings) {
        this.borrowings = borrowings;
    }
}
