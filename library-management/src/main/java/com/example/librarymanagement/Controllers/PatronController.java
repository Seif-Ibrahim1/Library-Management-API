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
    @Autowired
    private final PatronService patronService;

    public PatronController(PatronService patronService) {
        this.patronService = patronService;
    }

    @GetMapping
    public ResponseEntity<List<Patron>> getAllPatrons() {
        List<Patron> patrons = patronService.getAllPatrons();
        return new ResponseEntity<>(patrons, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patron> getPatronById(@PathVariable Long id) {
        Patron patron = patronService.getPatron(id);
        if (patron != null) {
            return new ResponseEntity<>(patron, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Patron> addPatron(@Valid @RequestBody Patron patron) {
        patronService.addPatron(patron);
        return new ResponseEntity<>(patron, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patron> updatePatron(@PathVariable Long id, @RequestBody Patron patron) {
        Patron updatedPatron = patronService.updatePatron(id, patron);
        if (updatedPatron != null) {
            return new ResponseEntity<>(updatedPatron, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatron(@PathVariable Long id) {
        patronService.deletePatron(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}