package com.example.librarymanagement.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.librarymanagement.Entities.Patron;

// JpaRepository is a Spring Data interface providing CRUD operations for the Patron entity
// It also supports pagination, sorting, and other query methods out-of-the-box
public interface PatronRepository extends JpaRepository<Patron, Long> {
    // This interface inherits various methods like save(), findById(), findAll(), deleteById(), etc.
    // JpaRepository<Patron, Long> signifies that this repository works with entities of type Patron and primary key of type Long
}
