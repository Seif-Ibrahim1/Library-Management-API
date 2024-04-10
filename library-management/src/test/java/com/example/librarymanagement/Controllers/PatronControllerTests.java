package com.example.librarymanagement.Controllers;

import com.example.librarymanagement.Entities.Patron;
import com.example.librarymanagement.Services.PatronService;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class PatronControllerTests {

    @Mock
    private PatronService patronService;

    @InjectMocks
    private PatronController patronController;

    @Test
    public void testGetAllPatrons() {
        // Mock data
        List<Patron> patrons = new ArrayList<>();
        patrons.add(new Patron("John Doe", "john@example.com"));
        patrons.add(new Patron("Jane Smith", "jane@example.com"));

        // Mock service method
        when(patronService.getAllPatrons()).thenReturn((ArrayList<Patron>)patrons);

        // Invoke controller method
        ResponseEntity<List<Patron>> response = patronController.getAllPatrons();

        // Check response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(patrons, response.getBody());
    }

    @Test
    public void testGetPatronById() {
        // Mock data
        Long id = 1L;
        Patron patron = new Patron("John Doe", "john@example.com");

        // Mock service method
        when(patronService.getPatron(id)).thenReturn(patron);

        // Invoke controller method
        ResponseEntity<Patron> response = patronController.getPatronById(id);

        // Check response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(patron, response.getBody());
    }

    @Test
    public void testAddPatron() {
        // Mock data
        Patron newPatron = new Patron("John Doe", "john@example.com");

        // Invoke controller method
        ResponseEntity<Patron> response = patronController.addPatron(newPatron);

        // Verify service method was called with correct parameter
        verify(patronService).addPatron(newPatron);

        // Check response
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(newPatron, response.getBody());
    }

    @Test
    public void testUpdatePatron() {
        // Mock data
        Long id = 1L;
        Patron updatedPatron = new Patron("John Doe", "john@example.com");

        // Mock service method
        when(patronService.updatePatron(id, updatedPatron)).thenReturn(updatedPatron);

        // Invoke controller method
        ResponseEntity<Patron> response = patronController.updatePatron(id, updatedPatron);

        // Verify service method was called with correct parameters
        verify(patronService).updatePatron(id, updatedPatron);

        // Check response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedPatron, response.getBody());
    }

    @Test
    public void testDeletePatron() {
        // Mock data
        Long id = 1L;

        // Invoke controller method
        ResponseEntity<Void> response = patronController.deletePatron(id);

        // Verify service method was called with correct parameter
        verify(patronService).deletePatron(id);

        // Check response
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
