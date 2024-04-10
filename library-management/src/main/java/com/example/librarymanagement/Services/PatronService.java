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

    public PatronService(PatronRepository patronRepository) {
        this.patronRepository = patronRepository;
    }

    public void addPatron(Patron patron) {
        patronRepository.save(patron);
    }

    public void deletePatron(Long id) {
        if (id != null && id > 0) {
            patronRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Invalid patron id provided");
        }
    }

    public Patron getPatron(Long id) {
        return patronRepository.findById(id).orElse(null);
    }

    public ArrayList<Patron> getAllPatrons() {
        return new ArrayList<>(patronRepository.findAll());
    }

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
