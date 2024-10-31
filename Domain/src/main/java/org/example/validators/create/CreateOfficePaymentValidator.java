package org.example.validators.create;

import org.example.contract.repository.OfficeRepo;
import org.example.contract.request.create.CreateOfficePaymentRequest;
import org.example.exceptions.ValidationErrorDetails;
import org.example.model.Office;
import org.example.model.OfficePayment;
import org.example.validators.update.ExceptionThrower;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import static org.example.contract.constant.DomainConstant.*;

public class CreateOfficePaymentValidator {
    private final OfficeRepo officeRepo;

    public CreateOfficePaymentValidator(OfficeRepo officeRepo) {
        this.officeRepo = officeRepo;
    }

    public void validate(CreateOfficePaymentRequest request){
        Set<ValidationErrorDetails> errorDetails = new HashSet<>();

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
            final Optional<Office> byId = officeRepo.findById(request.getOfficeId());
            if(byId.isEmpty()){
                errorDetails.add(new ValidationErrorDetails(OFFICE_FIELD, ELEMENT_NOT_FOUND));
            }
        }
        ExceptionThrower.throwIfNotEmpty(errorDetails);
    }
}
