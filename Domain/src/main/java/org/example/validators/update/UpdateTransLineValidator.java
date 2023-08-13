package org.example.validators.update;

import org.example.contract.repository.GasStationRepo;
import org.example.contract.repository.RefineryRepo;
import org.example.contract.request.update.UpdateTransLineRequest;
import org.example.exceptions.ValidationErrorDetails;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static org.example.contract.constant.DomainConstant.*;
import static org.example.contract.constant.DomainConstant.NULL_ERROR_MSG;

public class UpdateTransLineValidator {

    public final GasStationRepo gasStationRepo;
    private final RefineryRepo refineryRepo;

    public UpdateTransLineValidator(GasStationRepo gasStationRepo, RefineryRepo refineryRepo) {
        this.gasStationRepo = gasStationRepo;

        this.refineryRepo = refineryRepo;
    }

    public void validate(UpdateTransLineRequest request){
        Set<ValidationErrorDetails> errorDetails = new HashSet<>();

        if(Objects.isNull(request.getId())){
            errorDetails.add(new ValidationErrorDetails(ID_FIELD, NULL_ERROR_MSG));
        }

        if (gasStationRepo.findById(request.getDestinationId()).isEmpty() && refineryRepo.findById(request.getDestinationId()).isEmpty()) {
            errorDetails.add(new ValidationErrorDetails(DESTINATION_FIELD, ELEMENT_NOT_FOUND));
        }

        if (refineryRepo.findById(request.getSourceId()).isEmpty() && gasStationRepo.findById(request.getSourceId()).isEmpty()) {
            errorDetails.add(new ValidationErrorDetails(SOURCE_FIELD, ELEMENT_NOT_FOUND));
        }

        if(Objects.isNull(request.getFeeAmount())){
            errorDetails.add(new ValidationErrorDetails(FEE_FIELD, NULL_ERROR_MSG));
        }

        if (Objects.isNull(request.getFeeCurrency())) {
            errorDetails.add(new ValidationErrorDetails(Currency_FIELD, NULL_ERROR_MSG));
        }

        ExceptionThrower.throwIfNotEmpty(errorDetails);
    }
}
