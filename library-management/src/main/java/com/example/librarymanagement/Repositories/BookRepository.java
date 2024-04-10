package com.example.librarymanagement.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.librarymanagement.Entities.Book;

// JpaRepository is a Spring Data interface providing CRUD operations for the Book entity
// It also supports pagination, sorting, and other query methods out-of-the-box
public interface BookRepository extends JpaRepository<Book, Long> {
    // This interface inherits various methods like save(), findById(), findAll(), deleteById(), etc.
    // JpaRepository<Book, Long> signifies that this repository works with entities of type Book and primary key of type Long
}
