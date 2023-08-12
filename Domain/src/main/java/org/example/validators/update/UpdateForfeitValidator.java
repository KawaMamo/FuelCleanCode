package org.example.validators.update;

import org.example.contract.repository.PartitionRepo;
import org.example.contract.repository.VehicleRepo;
import org.example.contract.request.update.UpdateForfeitRequest;
import org.example.exceptions.ValidationErrorDetails;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static org.example.contract.constant.DomainConstant.*;
import static org.example.contract.constant.DomainConstant.NULL_ERROR_MSG;

public class UpdateForfeitValidator {

    private final PartitionRepo partitionRepo;
    private final VehicleRepo vehicleRepo;

    public UpdateForfeitValidator(PartitionRepo partitionRepo, VehicleRepo vehicleRepo) {
        this.partitionRepo = partitionRepo;
        this.vehicleRepo = vehicleRepo;
    }

    public void validate(UpdateForfeitRequest request){

        Set<ValidationErrorDetails> errorDetails = new HashSet<>();

        if(Objects.isNull(request.getId())){
            errorDetails.add(new ValidationErrorDetails(ID_FIELD, NULL_ERROR_MSG));
        }

        if(partitionRepo.findById(request.getPartitionId()).isEmpty()){
            errorDetails.add(new ValidationErrorDetails(PARTITION_FIELD, ELEMENT_NOT_FOUND));
        }

        if(Objects.isNull(request.getPrice())){
            errorDetails.add(new ValidationErrorDetails(PRICE_FIELD, NULL_ERROR_MSG));
        }

        if(Objects.isNull(request.getReason())){
            errorDetails.add(new ValidationErrorDetails(Currency_FIELD, NULL_ERROR_MSG));
        }

        if(vehicleRepo.findById(request.getVehiclesId()).isEmpty()){
            errorDetails.add(new ValidationErrorDetails(VEHICLE_FIELD, NULL_ERROR_MSG));
        }

        if(Objects.isNull(request.getReason())){
            errorDetails.add(new ValidationErrorDetails(REASON_FIELD, NULL_ERROR_MSG));
        }

        ExceptionThrower.throwIfNotEmpty(errorDetails);
    }
}
