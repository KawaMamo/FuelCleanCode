package org.example.validators.update;

import org.example.contract.repository.MaterialRepo;
import org.example.contract.repository.PriceCategoryRepo;
import org.example.contract.request.update.UpdateCategoryRequest;
import org.example.exceptions.ValidationErrorDetails;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static org.example.contract.constant.DomainConstant.*;

public class UpdateCategoryValidator {

    private final PriceCategoryRepo priceCategoryRepo;
    public final MaterialRepo materialRepo;

    public UpdateCategoryValidator(PriceCategoryRepo priceCategoryRepo, MaterialRepo materialRepo) {
        this.priceCategoryRepo = priceCategoryRepo;
        this.materialRepo = materialRepo;
    }

    public void validate(UpdateCategoryRequest request){
        Set<ValidationErrorDetails> errorDetails = new HashSet<>();

        if(Objects.isNull(request.getId())){
            errorDetails.add(new ValidationErrorDetails(ID_FIELD, NULL_ERROR_MSG));
        }
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

        materialRepo.findById(request.getMaterialId())
                .ifPresentOrElse(material -> {}, ()-> errorDetails.add(new ValidationErrorDetails(MATERIAL_FIELD, ELEMENT_NOT_FOUND)));

        ExceptionThrower.throwIfNotEmpty(errorDetails);
    }
}
