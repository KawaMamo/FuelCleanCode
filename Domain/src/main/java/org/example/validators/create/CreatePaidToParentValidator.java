package org.example.validators.create;

import org.example.contract.request.create.CreatePaidToParentRequest;
import org.example.exceptions.ValidationErrorDetails;
import org.example.validators.update.ExceptionThrower;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static org.example.contract.constant.DomainConstant.*;
import static org.example.contract.constant.DomainConstant.NULL_ERROR_MSG;

public class CreatePaidToParentValidator {

    public void validate(CreatePaidToParentRequest request){
        Set<ValidationErrorDetails> errorDetails = new HashSet<>();

        if(Objects.isNull(request.getAmount())){
            errorDetails.add(new ValidationErrorDetails(AMOUNT_FIELD, NULL_ERROR_MSG));
        }

        if(Objects.isNull(request.getBillNumber()))
            errorDetails.add(new ValidationErrorDetails(BILL_NUMBER_FIELD, NULL_ERROR_MSG));

        if(Objects.isNull(request.getNotes())){
            errorDetails.add(new ValidationErrorDetails(NOTES_FIELD, NULL_ERROR_MSG));
        }
        ExceptionThrower.throwIfNotEmpty(errorDetails);
    }
}
