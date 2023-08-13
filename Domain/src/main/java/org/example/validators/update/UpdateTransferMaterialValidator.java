package org.example.validators.update;

import org.example.contract.repository.GasStationRepo;
import org.example.contract.repository.MaterialRepo;
import org.example.contract.request.update.UpdateTransferMaterialRequest;
import org.example.exceptions.ValidationErrorDetails;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static org.example.contract.constant.DomainConstant.*;

public class UpdateTransferMaterialValidator {
    private final MaterialRepo materialRepo;
    private final GasStationRepo gasStationRepo;

    public UpdateTransferMaterialValidator(MaterialRepo materialRepo, GasStationRepo gasStationRepo) {
        this.materialRepo = materialRepo;
        this.gasStationRepo = gasStationRepo;
    }

    public void validate(UpdateTransferMaterialRequest request){
        Set<ValidationErrorDetails> errorDetails = new HashSet<>();

        if(Objects.isNull(request.getId())){
            errorDetails.add(new ValidationErrorDetails(ID_FIELD, NULL_ERROR_MSG));
        }

        if(Objects.isNull(request.getMaterialId()) || materialRepo.findById(request.getMaterialId()).isEmpty()){
            errorDetails.add(new ValidationErrorDetails(MATERIAL_FIELD, ELEMENT_NOT_FOUND));
        }

        if(Objects.isNull(request.getSourceId()) || gasStationRepo.findById(request.getSourceId()).isEmpty()){
            errorDetails.add(new ValidationErrorDetails(SOURCE_FIELD, ELEMENT_NOT_FOUND));
        }

        if(Objects.isNull(request.getDestinationId()) || gasStationRepo.findById(request.getDestinationId()).isEmpty()){
            errorDetails.add(new ValidationErrorDetails(DESTINATION_FIELD, ELEMENT_NOT_FOUND));
        }

        if(Objects.isNull(request.getAmount())){
            errorDetails.add(new ValidationErrorDetails(AMOUNT_FIELD, NULL_ERROR_MSG));
        }

        if(Objects.isNull(request.getPrice().getAmount())){
            errorDetails.add(new ValidationErrorDetails(PRICE_FIELD+"_"+AMOUNT_FIELD, NULL_ERROR_MSG));
        }

        if(Objects.isNull(request.getPrice().getCurrency())){
            errorDetails.add(new ValidationErrorDetails(Currency_FIELD, NULL_ERROR_MSG));
        }

        ExceptionThrower.throwIfNotEmpty(errorDetails);
    }
}
