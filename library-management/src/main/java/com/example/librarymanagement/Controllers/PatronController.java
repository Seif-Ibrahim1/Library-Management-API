package com.example.librarymanagement.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.librarymanagement.Entities.Patron;
import com.example.librarymanagement.Services.PatronService;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/patrons")
@Validated
public class PatronController {
    // Autowire PatronService to handle business logic related to patrons
    @Autowired
    private final PatronService patronService;

    // Constructor injection for PatronService
    public PatronController(PatronService patronService) {
        this.patronService = patronService;
    }

    // Handler for HTTP GET request to retrieve all patrons
    @GetMapping
    public ResponseEntity<List<Patron>> getAllPatrons() {
        // Retrieve list of patrons from PatronService
        List<Patron> patrons = patronService.getAllPatrons();
        // Return list of patrons with HTTP status OK
        return new ResponseEntity<>(patrons, HttpStatus.OK);
    }

    // Handler for HTTP GET request to retrieve a specific patron by ID
    @GetMapping("/{id}")
    public ResponseEntity<Patron> getPatronById(@PathVariable Long id) {
        // Retrieve the patron with the given ID from PatronService
        Patron patron = patronService.getPatron(id);
        // Check if patron is found
        if (patron != null) {
            // Return patron with HTTP status OK
            return new ResponseEntity<>(patron, HttpStatus.OK);
        } else {
            // Return HTTP status NOT_FOUND if patron is not found
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Handler for HTTP POST request to add a new patron
    @PostMapping
    public ResponseEntity<Patron> addPatron(@Valid @RequestBody Patron patron) {
        // Add the new patron using PatronService
        patronService.addPatron(patron);
        // Return the added patron with HTTP status CREATED
        return new ResponseEntity<>(patron, HttpStatus.CREATED);
    }

    // Handler for HTTP PUT request to update an existing patron
    @PutMapping("/{id}")
    public ResponseEntity<Patron> updatePatron(@PathVariable Long id, @RequestBody Patron patron) {
        // Update the patron with the given ID using PatronService
        Patron updatedPatron = patronService.updatePatron(id, patron);
        // Check if patron is updated successfully
        if (updatedPatron != null) {
            // Return the updated patron with HTTP status OK
            return new ResponseEntity<>(updatedPatron, HttpStatus.OK);
        } else {
            // Return HTTP status NOT_FOUND if patron is not found
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Handler for HTTP DELETE request to delete a patron by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatron(@PathVariable Long id) {
        // Delete the patron with the given ID using PatronService
        patronService.deletePatron(id);
        // Return HTTP status NO_CONTENT indicating successful deletion
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
