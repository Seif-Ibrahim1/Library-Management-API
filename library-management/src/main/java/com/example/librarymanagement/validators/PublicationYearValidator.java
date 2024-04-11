package com.example.librarymanagement.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class PublicationYearValidator implements ConstraintValidator<ValidPublicationYear, Integer> {

    @Override
    public void initialize(ValidPublicationYear constraintAnnotation) {
    }

    @Override
    public boolean isValid(Integer publicationYear, ConstraintValidatorContext context) {
        if (publicationYear == null) {
            return true; // Null values are handled by other constraints
        }
        int currentYear = LocalDate.now().getYear();
        return publicationYear <= currentYear;
    }
}
