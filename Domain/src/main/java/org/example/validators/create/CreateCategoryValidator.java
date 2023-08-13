package org.example.validators.create;

import org.example.contract.repository.PriceCategoryRepo;
import org.example.contract.request.create.CreateCategoryRequest;
import org.example.exceptions.ValidationErrorDetails;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static org.example.contract.constant.DomainConstant.*;
import static org.example.validators.update.ExceptionThrower.throwIfNotEmpty;

public class CreateCategoryValidator {

    private final PriceCategoryRepo priceCategoryRepo;

    public CreateCategoryValidator(PriceCategoryRepo priceCategoryRepo) {
        this.priceCategoryRepo = priceCategoryRepo;
    }

    public void validate(CreateCategoryRequest request){
        Set<ValidationErrorDetails> errorDetails = new HashSet<>();

        if(Objects.isNull(request.getPriceCategoryId())){
            errorDetails.add(new ValidationErrorDetails(PRICE_CATEGORY_FIELD, NULL_ERROR_MSG));
        }else if (request.getPriceCategoryId()<=0){
            errorDetails.add(new ValidationErrorDetails(PRICE_CATEGORY_FIELD, ILLEGAL_VALUE));
        }

        if(Objects.isNull(request.getMaterialId()) || request.getMaterialId()<=0){
            errorDetails.add(new ValidationErrorDetails(MATERIAL_FIELD, ILLEGAL_VALUE));
        }

        priceCategoryRepo.findById(request.getPriceCategoryId()).ifPresentOrElse( (s)->{},
                ()-> errorDetails.add(new ValidationErrorDetails(PRICE_CATEGORY_FIELD, ELEMENT_NOT_FOUND)));

        throwIfNotEmpty(errorDetails);
    }


}
