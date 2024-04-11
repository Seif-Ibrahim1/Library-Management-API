package com.example.librarymanagement.Services;

import java.util.ArrayList;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.librarymanagement.Entities.Book;
import com.example.librarymanagement.Repositories.BookRepository;

/**
 * Service class for managing book-related operations.
 */
@Service
public class BookService {
    private final BookRepository bookRepository;

    /**
     * Constructor for BookService.
     * @param bookRepository The repository for accessing book data.
     */
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    
    /**
     * Method to add a new book.
     * @param book The book object to add.
     */
    public void addBook(Book book) {
        bookRepository.save(book);
    }

    /**
     * Method to delete a book by its ID.
     * @param id The ID of the book to delete.
     */
    public void deleteBook(Long id) {
        if (id != null && id > 0) {
            bookRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Invalid book id provided");
        }
    }

    /**
     * Method to retrieve a book by its ID.
     * @param id The ID of the book to retrieve.
     * @return The book object if found, otherwise null.
     */
    public Book getBook(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    /**
     * Method to retrieve all books.
     * @return ArrayList containing all books.
     */
    @Cacheable("books")
    public ArrayList<Book> getBooks() {
        return new ArrayList<>(bookRepository.findAll());
    }

    /**
     * Method to update an existing book.
     * @param id The ID of the book to update.
     * @param book The updated book object.
     * @return The updated book object if successful, otherwise null.
     */
    public Book updateBook(Long id, Book book) {
        Book existingBook = bookRepository.findById(id).orElse(null);
        if (existingBook != null) {
            if (book.getTitle() != null) {
                existingBook.setTitle(book.getTitle());
            }

            if (book.getAuthor() != null) {
                existingBook.setAuthor(book.getAuthor());
            }

            if (book.getIsbn() != null) {
                existingBook.setIsbn(book.getIsbn());
            }

            if (book.getPublicationYear() != null && book.getPublicationYear() != 0) {
                existingBook.setPublicationYear(book.getPublicationYear());
            }

            bookRepository.save(existingBook);
        }
        return existingBook;
    }
}
