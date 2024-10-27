package org.example.validators.update;

import org.example.contract.repository.OfficePaymentRepo;
import org.example.contract.request.update.UpdateOfficePaymentRequest;
import org.example.exceptions.ValidationErrorDetails;
import org.example.model.OfficePayment;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import static org.example.contract.constant.DomainConstant.*;
import static org.example.contract.constant.DomainConstant.ELEMENT_NOT_FOUND;

public class UpdateOfficePaymentValidator {
    private final OfficePaymentRepo officePaymentRepo;

    public UpdateOfficePaymentValidator(OfficePaymentRepo officePaymentRepo) {
        this.officePaymentRepo = officePaymentRepo;
    }

    public void validate(UpdateOfficePaymentRequest request){
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

        if(Objects.isNull(request.getOfficeId())) {
            errorDetails.add(new ValidationErrorDetails(OFFICE_FIELD, NULL_ERROR_MSG));
        }else {
            final Optional<OfficePayment> byId = officePaymentRepo.findById(request.getOfficeId());
            if(byId.isEmpty()){
                errorDetails.add(new ValidationErrorDetails(OFFICE_FIELD, ELEMENT_NOT_FOUND));
            }
        }
        ExceptionThrower.throwIfNotEmpty(errorDetails);
    }
}
