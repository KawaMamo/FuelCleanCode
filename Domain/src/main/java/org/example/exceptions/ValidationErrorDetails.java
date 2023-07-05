package org.example.exceptions;

import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class ValidationErrorDetails {

    private String fieldName;
    private String errorMessage;

}
