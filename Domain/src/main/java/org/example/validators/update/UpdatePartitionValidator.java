package org.example.validators.update;

import org.example.contract.repository.GasStationRepo;
import org.example.contract.repository.MaterialRepo;
import org.example.contract.repository.TransRepo;
import org.example.contract.request.update.UpdatePartitionRequest;
import org.example.exceptions.DomainValidationException;
import org.example.exceptions.ValidationErrorDetails;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static org.example.contract.constant.DomainConstant.*;
import static org.example.contract.constant.DomainConstant.ELEMENT_NOT_FOUND;

public class UpdatePartitionValidator {

    private final MaterialRepo materialRepo;
    private final GasStationRepo gasStationRepo;
    private final TransRepo transRepo;

    public UpdatePartitionValidator(MaterialRepo materialRepo,
                                    GasStationRepo gasStationRepo,
                                    TransRepo transRepo) {
        this.materialRepo = materialRepo;
        this.gasStationRepo = gasStationRepo;
        this.transRepo = transRepo;
    }

    public void validate(UpdatePartitionRequest request){
        Set<ValidationErrorDetails> errorDetails = new HashSet<>();

        if(Objects.isNull(request.getId())){
            errorDetails.add(new ValidationErrorDetails(ID_FIELD, NULL_ERROR_MSG));
        }

        if (Objects.isNull(request.getAmount())) {
            errorDetails.add(new ValidationErrorDetails(AMOUNT_FIELD, NULL_ERROR_MSG));
        }

        if(materialRepo.findById(request.getMaterialId()).isEmpty()){
            errorDetails.add(new ValidationErrorDetails(MATERIAL_FIELD, ELEMENT_NOT_FOUND));
        }

        if(gasStationRepo.findById(request.getGasStationId()).isEmpty()){
            errorDetails.add(new ValidationErrorDetails(GAS_STATION_FIELD, ELEMENT_NOT_FOUND));
        }

        if(transRepo.findByIdAndDeletedAt(request.getTransportationId(), null).isEmpty()){
            errorDetails.add(new ValidationErrorDetails(TRANS_FIELD, ELEMENT_NOT_FOUND));
        }

        if(!errorDetails.isEmpty()){
            throw new DomainValidationException(errorDetails);
        }
    }
}
