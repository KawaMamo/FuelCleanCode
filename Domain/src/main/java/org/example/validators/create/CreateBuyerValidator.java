package org.example.validators.create;

import org.example.contract.request.create.CreateBuyerRequest;
import org.example.exceptions.ValidationErrorDetails;
import org.example.validators.update.ExceptionThrower;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static org.example.contract.constant.DomainConstant.NAME_FIELD;
import static org.example.contract.constant.DomainConstant.NULL_ERROR_MSG;

public class CreateBuyerValidator {

    public void validate(CreateBuyerRequest request){
        Set<ValidationErrorDetails> errorDetails = new HashSet<>();

        if(Objects.isNull(request.getName())){
            errorDetails.add(new ValidationErrorDetails(NAME_FIELD, NULL_ERROR_MSG));
        }

        if(Objects.isNull(request.getOrganization())){
            errorDetails.add(new ValidationErrorDetails(NAME_FIELD, NULL_ERROR_MSG));
        }

        ExceptionThrower.throwIfNotEmpty(errorDetails);

    }
}
