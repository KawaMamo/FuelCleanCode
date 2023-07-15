package org.example.validators;

import org.example.contract.constant.DomainConstant;
import org.example.contract.repository.RefineryRepo;
import org.example.contract.repository.VehicleRepo;
import org.example.contract.request.CreateTransRequest;
import org.example.exceptions.DomainValidationException;
import org.example.exceptions.ValidationErrorDetails;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static org.example.contract.constant.DomainConstant.*;

public class CreateTransValidator {

    private final RefineryRepo refineryRepo;
    private final VehicleRepo vehicleRepo;

    public CreateTransValidator(RefineryRepo refineryRepo, VehicleRepo vehicleRepo) {
        this.refineryRepo = refineryRepo;
        this.vehicleRepo = vehicleRepo;
    }

    public void validate(CreateTransRequest request){
        Set<ValidationErrorDetails> errorDetails = new HashSet<>();

        if(refineryRepo.findById(request.getRefinery_id()).isEmpty()){
            errorDetails.add(new ValidationErrorDetails(REFINERY_FIELD, ELEMENT_NOT_FOUND));
        }

        if(vehicleRepo.findById(request.getVehicle_id()).isEmpty()){
            errorDetails.add(new ValidationErrorDetails(VEHICLE_FIELD, ELEMENT_NOT_FOUND));
        }

        if(Objects.isNull(request.getSize())){
            errorDetails.add(new ValidationErrorDetails(SIZE_FIELD, NULL_ERROR_MSG));
        }

        if(Objects.isNull(request.getType())){
            errorDetails.add(new ValidationErrorDetails(TYPE_ENUM, ILLEGAL_VALUE));
        }

        if(!errorDetails.isEmpty()){
            throw new DomainValidationException(errorDetails);
        }

    }
}
