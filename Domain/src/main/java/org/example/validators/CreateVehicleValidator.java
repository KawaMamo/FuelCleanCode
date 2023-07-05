package org.example.validators;

import org.example.contract.request.CreateVehicleRequest;
import org.example.exceptions.DomainValidationException;
import org.example.exceptions.ValidationErrorDetails;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import static org.example.contract.constant.DomainConstant.*;
import static org.example.contract.constant.DomainConstant.OFFICE_FIELD;

public class CreateVehicleValidator {

    public void validate(CreateVehicleRequest request){
        Set<ValidationErrorDetails> validationErrorDetails = new HashSet<>();

        if(Objects.isNull(request.getPlateNumber())){
            validationErrorDetails.add(new ValidationErrorDetails(PLATE_NUMBER_FIELD, NULL_ERROR_MSG));
        }

        if(request.getOffice_id()<=0){
            validationErrorDetails.add(new ValidationErrorDetails(OFFICE_FIELD, ILLEGAL_VALUE));
        }

        if(request.getDriver_id()<=0){
             validationErrorDetails.add(new ValidationErrorDetails(DRIVER_FIELD, ILLEGAL_VALUE));
        }

        if(request.getSize()<=0){
            validationErrorDetails.add(new ValidationErrorDetails(SIZE_FIELD, ILLEGAL_VALUE));
        }

        if(request.getTrafficCenter_id()<=0){
            validationErrorDetails.add(new ValidationErrorDetails(TRAFFIC_CENTER, ILLEGAL_VALUE));
        }

        if(!validationErrorDetails.isEmpty())
            throw new DomainValidationException(validationErrorDetails);

    }
}
