package org.example.exceptions;

import java.util.Set;

public class DomainValidationException {

    private Set<ValidationErrorDetails> validationErrors;

    public DomainValidationException(Set<ValidationErrorDetails> validationErrors) {
        this.validationErrors = validationErrors;
    }

}
