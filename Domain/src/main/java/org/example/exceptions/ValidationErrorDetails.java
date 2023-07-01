package org.example.exceptions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ValidationErrorDetails {

    private String fieldName;
    private String errorMessage;

}
