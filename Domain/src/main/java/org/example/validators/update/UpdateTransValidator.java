package org.example.validators.update;

import org.example.contract.repository.RefineryRepo;
import org.example.contract.repository.VehicleRepo;
import org.example.contract.request.update.UpdateTransRequest;
import org.example.exceptions.ValidationErrorDetails;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static org.example.contract.constant.DomainConstant.*;
import static org.example.contract.constant.DomainConstant.ILLEGAL_VALUE;

public class UpdateTransValidator {

    private final RefineryRepo refineryRepo;
    private final VehicleRepo vehicleRepo;

    public UpdateTransValidator(RefineryRepo refineryRepo, VehicleRepo vehicleRepo) {
        this.refineryRepo = refineryRepo;
        this.vehicleRepo = vehicleRepo;
    }

    public void validate(UpdateTransRequest request){
        Set<ValidationErrorDetails> errorDetails = new HashSet<>();

        if(Objects.isNull(request.getId())){
            errorDetails.add(new ValidationErrorDetails(ID_FIELD, NULL_ERROR_MSG));
        }

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

        ExceptionThrower.throwIfNotEmpty(errorDetails);

    }
}
