package org.example.validators.update;

import org.example.contract.repository.RefineryRepo;
import org.example.contract.repository.TransRepo;
import org.example.contract.repository.VehicleRepo;
import org.example.contract.request.update.UpdateTransRequest;
import org.example.exceptions.ValidationErrorDetails;
import org.example.model.Partition;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static org.example.contract.constant.DomainConstant.*;
import static org.example.contract.constant.DomainConstant.ILLEGAL_VALUE;

public class UpdateTransValidator {

    private final RefineryRepo refineryRepo;
    private final VehicleRepo vehicleRepo;
    private final TransRepo transRepo;

    public UpdateTransValidator(RefineryRepo refineryRepo, VehicleRepo vehicleRepo, TransRepo transRepo) {
        this.refineryRepo = refineryRepo;
        this.vehicleRepo = vehicleRepo;
        this.transRepo = transRepo;
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

        transRepo.findByIdAndDeletedAt(request.getId(), null).ifPresent(transportation -> {
            Long size = 0L;
            for (Partition partition : transportation.getPartitions()) {
                size += partition.getAmount();
            if(size>request.getSize()) {
                errorDetails.add(new ValidationErrorDetails(SIZE_FIELD, ILLEGAL_VALUE));
            }
            }
        });


        ExceptionThrower.throwIfNotEmpty(errorDetails);

    }
}
