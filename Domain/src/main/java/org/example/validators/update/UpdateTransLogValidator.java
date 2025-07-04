package org.example.validators.update;

import org.example.contract.repository.TransLineRepo;
import org.example.contract.repository.TransRepo;
import org.example.contract.repository.VehicleRepo;
import org.example.contract.request.update.UpdateTransLogRequest;
import org.example.exceptions.ValidationErrorDetails;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static org.example.contract.constant.DomainConstant.*;
import static org.example.contract.constant.DomainConstant.NULL_ERROR_MSG;

public class UpdateTransLogValidator {

    private final TransLineRepo transLineRepo;
    private final VehicleRepo vehicleRepo;
    private final TransRepo transRepo;

    public UpdateTransLogValidator(TransLineRepo transLineRepo,
                                   VehicleRepo vehicleRepo,
                                   TransRepo transRepo) {
        this.transLineRepo = transLineRepo;
        this.vehicleRepo = vehicleRepo;
        this.transRepo = transRepo;
    }

    public void validate(UpdateTransLogRequest request){
        Set<ValidationErrorDetails> errorDetails = new HashSet<>();

        if(Objects.isNull(request.getId())){
            errorDetails.add(new ValidationErrorDetails(ID_FIELD, NULL_ERROR_MSG));
        }

        if(Objects.isNull(transLineRepo.findById(request.getTransLineId()))){
            errorDetails.add(new ValidationErrorDetails(TRANS_LINE_FIELD, ELEMENT_NOT_FOUND));
        }

        if (Objects.isNull(vehicleRepo.findById(request.getVehicleId()))) {
            errorDetails.add(new ValidationErrorDetails(VEHICLE_FIELD, ELEMENT_NOT_FOUND));
        }

        if(Objects.isNull(transRepo.findByIdAndDeletedAt(request.getTransportationId(), null))){
            errorDetails.add(new ValidationErrorDetails(TRANS_LINE_FIELD, ELEMENT_NOT_FOUND));
        }

        if(Objects.isNull(request.getFees())){
            errorDetails.add(new ValidationErrorDetails(FEE_FIELD, NULL_ERROR_MSG));
        }

        if(Objects.isNull(request.getNotes())){
            errorDetails.add(new ValidationErrorDetails(NOTES_FIELD, NULL_ERROR_MSG));
        }

        ExceptionThrower.throwIfNotEmpty(errorDetails);
    }
}
