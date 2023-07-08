package org.example.validators;

import org.example.contract.constant.DomainConstant;
import org.example.contract.request.CreateGroupRequest;
import org.example.exceptions.DomainValidationException;
import org.example.exceptions.ValidationErrorDetails;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static org.example.contract.constant.DomainConstant.*;

public class CreateGroupValidator {
    public void validate(CreateGroupRequest request){
        Set<ValidationErrorDetails> errorDetails = new HashSet<>();

        if(Objects.isNull(request.getName())){
            errorDetails.add(new ValidationErrorDetails(NAME_FIELD, NULL_ERROR_MSG));
        }

        if(!errorDetails.isEmpty()){
            throw new DomainValidationException(errorDetails);
        }
    }
}
