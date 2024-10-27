package org.example.validators.update;

import org.example.contract.repository.GasStationRepo;
import org.example.contract.request.update.UpdateClientPaymentRequest;
import org.example.exceptions.ValidationErrorDetails;
import org.example.model.GasStation;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import static org.example.contract.constant.DomainConstant.*;
import static org.example.contract.constant.DomainConstant.ELEMENT_NOT_FOUND;

public class UpdateClientPaymentValidator {
    public final GasStationRepo gasStationRepo;

    public UpdateClientPaymentValidator(GasStationRepo gasStationRepo) {
        this.gasStationRepo = gasStationRepo;
    }

    public void validate(UpdateClientPaymentRequest request){
        Set<ValidationErrorDetails> errorDetails = new HashSet<>();

        if(Objects.isNull(request.getId())){
            errorDetails.add(new ValidationErrorDetails(ID_FIELD, NULL_ERROR_MSG));
        }

        if(Objects.isNull(request.getAmount())){
            errorDetails.add(new ValidationErrorDetails(AMOUNT_FIELD, NULL_ERROR_MSG));
        }

        if(Objects.isNull(request.getBillNumber()))
            errorDetails.add(new ValidationErrorDetails(BILL_NUMBER_FIELD, NULL_ERROR_MSG));

        if(Objects.isNull(request.getNotes())){
            errorDetails.add(new ValidationErrorDetails(NOTES_FIELD, NULL_ERROR_MSG));
        }
        if(Objects.isNull(request.getGasStationId())){
            errorDetails.add(new ValidationErrorDetails(GAS_STATION_FIELD, NULL_ERROR_MSG));
        }else {
            final Optional<GasStation> byId = gasStationRepo.findById(request.getGasStationId());
            if(byId.isEmpty())
                errorDetails.add(new ValidationErrorDetails(GAS_STATION_FIELD, ELEMENT_NOT_FOUND));
        }
        ExceptionThrower.throwIfNotEmpty(errorDetails);
    }
}
