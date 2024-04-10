package com.example.librarymanagement.Repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.librarymanagement.Entities.Patron;

public interface PatronRepository extends JpaRepository<Patron , Long> {
    
}