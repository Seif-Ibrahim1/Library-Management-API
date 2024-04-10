package com.example.librarymanagement.Services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.librarymanagement.Entities.Book;
import com.example.librarymanagement.Repositories.BookRepository;

@Service
public class BookService {
    @Autowired
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    
    public void addBook(Book book) {
        bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        if (id != null && id > 0) {
            bookRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Invalid book id provided");
        }
    }

    public Book getBook(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public ArrayList<Book> getBooks() {
        return new ArrayList<>(bookRepository.findAll());
    }

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