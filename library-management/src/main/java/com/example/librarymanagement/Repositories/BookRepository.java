package com.example.librarymanagement.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.librarymanagement.Entities.Book;

/**
 * Repository interface for accessing and managing Book entities in the database.
 * JpaRepository provides CRUD operations and other query methods for the Book entity.
 */
public interface BookRepository extends JpaRepository<Book, Long> {
    // This interface inherits various methods like save(), findById(), findAll(), deleteById(), etc.
    // JpaRepository<Book, Long> signifies that this repository works with entities of type Book and primary key of type Long
}
