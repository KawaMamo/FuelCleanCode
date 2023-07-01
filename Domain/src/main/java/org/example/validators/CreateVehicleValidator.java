package org.example.validators;

import org.example.contract.constant.DomainConstant;
import org.example.contract.request.CreateVehicleRequest;
import org.example.exceptions.ValidationErrorDetails;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class CreateVehicleValidator {

    public void validate(CreateVehicleRequest request){
        Set<ValidationErrorDetails> validationErrorDetails = new HashSet<>();

        if(Objects.isNull(request.getPlateNumber())){
            validationErrorDetails.add(new ValidationErrorDetails(DomainConstant.PLATE_NUMBER_FIELD, DomainConstant.NULL_ERROR_MSG));
        }
    }
}
