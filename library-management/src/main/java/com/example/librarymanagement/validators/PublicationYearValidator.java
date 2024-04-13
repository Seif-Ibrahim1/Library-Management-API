package com.example.librarymanagement.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;

/**
 * Validator for the ValidPublicationYear annotation.
 */
public class PublicationYearValidator implements ConstraintValidator<ValidPublicationYear, Integer> {

    /**
     * Initializes the validator.
     * @param constraintAnnotation The annotation instance.
     */
    @Override
    public void initialize(ValidPublicationYear constraintAnnotation) {
        // No initialization logic needed for this validator
    }

    /**
     * Checks if the provided publication year is not in the future.
     * @param publicationYear The publication year to validate.
     * @param context The validation context.
     * @return True if the publication year is not in the future, false otherwise.
     */
    @Override
    public boolean isValid(Integer publicationYear, ConstraintValidatorContext context) {
        // Check if publicationYear is null
        if (publicationYear == null) {
            return true; // Null values are handled by other constraints
        }
        // Get the current year
        int currentYear = LocalDate.now().getYear();
        // Ensure publicationYear is not in the future
        return publicationYear <= currentYear;
    }
}
