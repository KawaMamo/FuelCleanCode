package org.example.validators.update;

import org.example.exceptions.DomainValidationException;
import org.example.exceptions.ValidationErrorDetails;

import java.util.Set;

public class ExceptionThrower {

    public static void throwIfNotEmpty(Set<ValidationErrorDetails> errorDetails) {
        if(!errorDetails.isEmpty()){
            throw new DomainValidationException(errorDetails);
        }
    }
}
