package org.example.validators;

import org.example.contract.request.CreatePartitionRequest;
import org.example.exceptions.DomainValidationException;
import org.example.exceptions.ValidationErrorDetails;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static org.example.contract.constant.DomainConstant.*;

public class CreatePartitionValidator {
    public void validate(CreatePartitionRequest request){
        Set<ValidationErrorDetails> errorDetails = new HashSet<>();

        if (Objects.isNull(request.getAmount())) {
            errorDetails.add(new ValidationErrorDetails(AMOUNT_FIELD, NULL_ERROR_MSG));
        }
        //TODO:check if material & gas station exist
        if(Objects.isNull(request.getMaterialId())){
            errorDetails.add(new ValidationErrorDetails(MATERIAL_FIELD, NULL_ERROR_MSG));
        }

        if(Objects.isNull(request.getGasStationId())){
            errorDetails.add(new ValidationErrorDetails(GAS_STATION_FIELD, NULL_ERROR_MSG));
        }

        if(!errorDetails.isEmpty()){
            throw new DomainValidationException(errorDetails);
        }
    }
}
