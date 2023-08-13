package org.example.validators.update;

import org.example.contract.request.update.UpdatePersonRequest;
import org.example.exceptions.ValidationErrorDetails;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static org.example.contract.constant.DomainConstant.*;
import static org.example.contract.constant.DomainConstant.NATIONAL_ID_RULES;

public class UpdatePersonValidator {

    public void validate(UpdatePersonRequest request){
        Set<ValidationErrorDetails> errorDetails = new HashSet<>();

        if(Objects.isNull(request.getId())){
            errorDetails.add(new ValidationErrorDetails(ID_FIELD, NULL_ERROR_MSG));
        }

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
        }else if(request.getNationalId().length() != 11 || Long.parseLong(request.getNationalId()) <= 0){
            errorDetails.add(new ValidationErrorDetails(NATIONAL_ID_FIELD, NATIONAL_ID_RULES));
        }

        ExceptionThrower.throwIfNotEmpty(errorDetails);
    }
}
