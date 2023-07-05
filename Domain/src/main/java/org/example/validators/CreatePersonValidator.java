package org.example.validators;

import org.example.contract.request.CreatePersonRequest;
import org.example.exceptions.DomainValidationException;
import org.example.exceptions.ValidationErrorDetails;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static org.example.contract.constant.DomainConstant.*;

public class CreatePersonValidator{
    public void validate(CreatePersonRequest request){
        Set<ValidationErrorDetails> errorDetails = new HashSet<>();

        if(Objects.isNull(request.getName())){
            errorDetails.add(new ValidationErrorDetails(NAME_FIELD, NULL_ERROR_MSG));
        }

        if(Objects.isNull(request.getFather())){
            errorDetails.add(new ValidationErrorDetails(FATHER_FIELD, NULL_ERROR_MSG));
        }

        if(Objects.isNull(request.getMother())){
            errorDetails.add(new ValidationErrorDetails(MOTHER_FIELD, NULL_ERROR_MSG));
        }

        if(Objects.isNull(request.getBirthDate())){
            errorDetails.add(new ValidationErrorDetails(BIRTH_DATE_FIELD, NULL_ERROR_MSG));
        }

        if(Objects.isNull(request.getBirthPlace())){
            errorDetails.add(new ValidationErrorDetails(BIRTH_PLACE, NULL_ERROR_MSG));
        }

        if(Objects.isNull(request.getBirthPlace())){
            errorDetails.add(new ValidationErrorDetails(BIRTH_PLACE, NULL_ERROR_MSG));
        }

        if(Objects.isNull(request.getBirthPlace())){
            errorDetails.add(new ValidationErrorDetails(BIRTH_PLACE, NULL_ERROR_MSG));
        }

        if(Objects.isNull(request.getNationalId())){
            errorDetails.add(new ValidationErrorDetails(NATIONAL_ID_FIELD, ILLEGAL_VALUE));
        }else if(request.getNationalId().length() != 11 || Long.parseLong(request.getNationalId()) > 0){
            errorDetails.add(new ValidationErrorDetails(NATIONAL_ID_FIELD, NATIONAL_ID_RULES));
        }

        if(!errorDetails.isEmpty()){
            throw new DomainValidationException(errorDetails);
        }
    }
}
