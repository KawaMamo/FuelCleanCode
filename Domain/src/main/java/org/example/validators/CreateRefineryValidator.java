package org.example.validators;

import org.example.contract.constant.DomainConstant;
import org.example.contract.request.CreateRefineryRequest;
import org.example.exceptions.ValidationErrorDetails;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class CreateRefineryValidator {

    public void validate(CreateRefineryRequest request){
        Set<ValidationErrorDetails> errorDetails = new HashSet<>();

        if(Objects.isNull(request.getName())){
            errorDetails.add(new ValidationErrorDetails(DomainConstant.REFINERY_FIELD, DomainConstant.NULL_ERROR_MSG));
        }

    }
}
