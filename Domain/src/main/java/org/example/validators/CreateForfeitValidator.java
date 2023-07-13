package org.example.validators;

import org.example.contract.constant.DomainConstant;
import org.example.contract.request.CreateForfeitRequest;
import org.example.exceptions.DomainValidationException;
import org.example.exceptions.ValidationErrorDetails;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static org.example.contract.constant.DomainConstant.*;

public class CreateForfeitValidator {
    public void validate(CreateForfeitRequest request){
        Set<ValidationErrorDetails> errorDetails = new HashSet<>();

        if(Objects.isNull(request.getPartitionId())){
            errorDetails.add(new ValidationErrorDetails(PARTITION_FIELD, NULL_ERROR_MSG));
        }

        if(Objects.isNull(request.getPriceAmount())){
            errorDetails.add(new ValidationErrorDetails(PRICE_FIELD, NULL_ERROR_MSG));
        }

        if(Objects.isNull(request.getPriceCurrency())){
            errorDetails.add(new ValidationErrorDetails(Currency_FIELD, NULL_ERROR_MSG));
        }

        if(Objects.isNull(request.getVehiclesId())){
            errorDetails.add(new ValidationErrorDetails(VEHICLE_FIELD, NULL_ERROR_MSG));
        }

        if(Objects.isNull(request.getReason())){
            errorDetails.add(new ValidationErrorDetails(REASON_FIELD, NULL_ERROR_MSG));
        }

        if(!errorDetails.isEmpty()){
            throw new DomainValidationException(errorDetails);
        }

    }
}
