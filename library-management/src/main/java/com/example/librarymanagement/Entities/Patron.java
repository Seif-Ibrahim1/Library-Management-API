package com.example.librarymanagement.Entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Represents a patron in the library management system.
 */
@Entity
@Table(name = "patrons")
public class Patron {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

    @OneToMany(mappedBy = "patron", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("patron")
    private List<Borrowing> borrowings;

    /**
     * Default constructor.
     */
    public Patron() {

    }

    /**
     * Parameterized constructor.
     * @param name The name of the patron.
     * @param email The email of the patron.
     */
    public Patron(String name, String email) {
        this.name = name;
        this.email = email;
    }

    /**
     * Retrieves the ID of the patron.
     * @return The ID of the patron.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the patron.
     * @param id The ID to set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retrieves the name of the patron.
     * @return The name of the patron.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the patron.
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the email of the patron.
     * @return The email of the patron.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the patron.
     * @param email The email to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retrieves the list of borrowings associated with the patron.
     * @return The list of borrowings associated with the patron.
     */
    public List<Borrowing> getBorrowings() {
        return borrowings;
    }

    /**
     * Sets the list of borrowings associated with the patron.
     * @param borrowings The list of borrowings to set.
     */
    public void setBorrowings(List<Borrowing> borrowings) {
        this.borrowings = borrowings;
    }
}
