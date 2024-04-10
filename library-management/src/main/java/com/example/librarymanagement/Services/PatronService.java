package com.example.librarymanagement.Services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.librarymanagement.Entities.Patron;
import com.example.librarymanagement.Repositories.PatronRepository;

@Service
public class PatronService {
    @Autowired
    private final PatronRepository patronRepository;

    // Constructor injection of PatronRepository
    public PatronService(PatronRepository patronRepository) {
        this.patronRepository = patronRepository;
    }

    // Method to add a new patron
    public void addPatron(Patron patron) {
        patronRepository.save(patron);
    }

    // Method to delete a patron by its ID
    public void deletePatron(Long id) {
        if (id != null && id > 0) {
            patronRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Invalid patron id provided");
        }
    }

    // Method to retrieve a patron by its ID
    public Patron getPatron(Long id) {
        return patronRepository.findById(id).orElse(null);
    }

    // Method to retrieve all patrons
    public ArrayList<Patron> getAllPatrons() {
        return new ArrayList<>(patronRepository.findAll());
    }

    // Method to update an existing patron
    public Patron updatePatron(Long id, Patron patron) {
        Patron existingPatron = patronRepository.findById(id).orElse(null);
        if (existingPatron != null) {
            if (patron.getName() != null) {
                existingPatron.setName(patron.getName());
            }

            if (patron.getEmail() != null) {
                existingPatron.setEmail(patron.getEmail());
            }

            patronRepository.save(existingPatron);
        }
        return existingPatron;
    }
}
