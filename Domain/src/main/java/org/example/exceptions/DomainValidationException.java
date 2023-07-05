package org.example.exceptions;

import java.util.Set;

public class DomainValidationException extends RuntimeException{

    private Set<ValidationErrorDetails> validationErrors;

    public DomainValidationException(Set<ValidationErrorDetails> validationErrors) {
        super(validationErrors.toString());
        this.validationErrors = validationErrors;
    }

}
