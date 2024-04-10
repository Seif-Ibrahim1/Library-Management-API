package com.example.librarymanagement.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.librarymanagement.Entities.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}