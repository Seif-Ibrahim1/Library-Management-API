package com.example.librarymanagement.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.librarymanagement.Entities.Book;
import com.example.librarymanagement.Entities.Patron;
import com.example.librarymanagement.Repositories.BookRepository;
import com.example.librarymanagement.Repositories.PatronRepository;

@Configuration
public class SeedData {

    @Bean
    public CommandLineRunner initData(BookRepository bookRepository, PatronRepository patronRepository) {
        return args -> {
            // Seed books
            Book book1 = new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925, "9780743273565");
            Book book2 = new Book("To Kill a Mockingbird", "Harper Lee", 1960, "9780061120084");
            bookRepository.save(book1);
            bookRepository.save(book2);

            // Seed patrons
            Patron patron1 = new Patron("John Doe", "john@example.com");
            Patron patron2 = new Patron("Jane Smith", "jane@example.com");
            patronRepository.save(patron1);
            patronRepository.save(patron2);
        };
    }
}
