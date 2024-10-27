package org.example.validators.create;

import org.example.contract.constant.DomainConstant;
import org.example.contract.repository.SellerRepo;
import org.example.contract.request.create.CreateSellerPaymentRequest;
import org.example.exceptions.ValidationErrorDetails;
import org.example.model.Seller;
import org.example.validators.update.ExceptionThrower;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import static org.example.contract.constant.DomainConstant.*;
import static org.example.contract.constant.DomainConstant.NULL_ERROR_MSG;

public class CreateSellerPaymentValidator {
    public final SellerRepo sellerRepo;

    public CreateSellerPaymentValidator(SellerRepo sellerRepo) {
        this.sellerRepo = sellerRepo;
    }

    public void validate(CreateSellerPaymentRequest request){
        Set<ValidationErrorDetails> errorDetails = new HashSet<>();

        if(Objects.isNull(request.getAmount())){
            errorDetails.add(new ValidationErrorDetails(AMOUNT_FIELD, NULL_ERROR_MSG));
        }

        if(Objects.isNull(request.getBillNumber()))
            errorDetails.add(new ValidationErrorDetails(BILL_NUMBER_FIELD, NULL_ERROR_MSG));

        if(Objects.isNull(request.getNotes())){
            errorDetails.add(new ValidationErrorDetails(NOTES_FIELD, NULL_ERROR_MSG));
        }
        if(Objects.isNull(request.getSellerId())){
            errorDetails.add(new ValidationErrorDetails(DomainConstant.SELLER_FIELD, NULL_ERROR_MSG));
        }else {
            final Optional<Seller> byId = sellerRepo.findById(request.getSellerId());
            if(byId.isEmpty()){
                errorDetails.add(new ValidationErrorDetails(SELLER_FIELD, ELEMENT_NOT_FOUND));
            }
        }

        ExceptionThrower.throwIfNotEmpty(errorDetails);
    }
}
