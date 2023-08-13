package org.example.validators.update;

import org.example.contract.request.update.UpdateRefineryRequest;
import org.example.exceptions.ValidationErrorDetails;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static org.example.contract.constant.DomainConstant.*;

public class UpdateRefineryValidator {
    public void validate(UpdateRefineryRequest request){
        Set<ValidationErrorDetails> errorDetails = new HashSet<>();

        if(Objects.isNull(request.getId())){
            errorDetails.add(new ValidationErrorDetails(ID_FIELD, NULL_ERROR_MSG));
        }

        if(Objects.isNull(request.getName())){
            errorDetails.add(new ValidationErrorDetails(REFINERY_FIELD, NULL_ERROR_MSG));
        }

        ExceptionThrower.throwIfNotEmpty(errorDetails);

    }
}
