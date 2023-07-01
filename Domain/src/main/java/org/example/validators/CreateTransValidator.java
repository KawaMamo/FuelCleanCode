package org.example.validators;

import org.example.contract.constant.DomainConstant;
import org.example.contract.request.CreateTransRequest;
import org.example.exceptions.DomainValidationException;
import org.example.exceptions.ValidationErrorDetails;
import org.example.model.TransportationType;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class CreateTransValidator {

    public void validate(CreateTransRequest request){
        Set<ValidationErrorDetails> errorDetails = new HashSet<>();

        if(Objects.isNull(request.getRefinery_id())){
            errorDetails.add(new ValidationErrorDetails(DomainConstant.REFINERY_FIELD, DomainConstant.NULL_ERROR_MSG));
        }

        if(Objects.isNull(request.getVehicle_id())){
            errorDetails.add(new ValidationErrorDetails(DomainConstant.VEHICLE_FIELD, DomainConstant.NULL_ERROR_MSG));
        }

        if(Objects.isNull(request.getSize())){
            errorDetails.add(new ValidationErrorDetails(DomainConstant.SIZE_FIELD, DomainConstant.NULL_ERROR_MSG));
        }

        if(Objects.isNull(request.getType())){
            errorDetails.add(new ValidationErrorDetails(DomainConstant.TYPE_ENUM, DomainConstant.ILLEGAL_VALUE));
        }

        if(!errorDetails.isEmpty()){
            new DomainValidationException(errorDetails);
        }

    }
}
