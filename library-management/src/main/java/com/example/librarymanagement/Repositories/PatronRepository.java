package com.example.librarymanagement.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.librarymanagement.Entities.Patron;

/**
 * Repository interface for accessing and managing Patron entities in the database.
 * JpaRepository provides CRUD operations and other query methods for the Patron entity.
 */
public interface PatronRepository extends JpaRepository<Patron, Long> {
    // This interface inherits various methods like save(), findById(), findAll(), deleteById(), etc.
    // JpaRepository<Patron, Long> signifies that this repository works with entities of type Patron and primary key of type Long
}
