package org.example.validators.create;

import org.example.contract.constant.DomainConstant;
import org.example.contract.repository.BuyerRepo;
import org.example.contract.repository.GasStationRepo;
import org.example.contract.repository.MaterialRepo;
import org.example.contract.request.create.CreateReturnedMaterialRequest;
import org.example.exceptions.ValidationErrorDetails;
import org.example.validators.update.ExceptionThrower;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static org.example.contract.constant.DomainConstant.*;

public class CreateReturnedMaterialValidator {

    private final GasStationRepo gasStationRepo;
    private final MaterialRepo materialRepo;
    private final BuyerRepo buyerRepo;

    public CreateReturnedMaterialValidator(GasStationRepo gasStationRepo, MaterialRepo materialRepo, BuyerRepo buyerRepo) {
        this.gasStationRepo = gasStationRepo;
        this.materialRepo = materialRepo;
        this.buyerRepo = buyerRepo;
    }

    public void validate(CreateReturnedMaterialRequest request){
        Set<ValidationErrorDetails> errorDetails = new HashSet<>();

        if(gasStationRepo.findById(request.getGasStationId()).isEmpty())
            errorDetails.add(new ValidationErrorDetails(ELEMENT_NOT_FOUND, request.getGasStationId().toString()));

        if(materialRepo.findById(request.getMaterialId()).isEmpty())
            errorDetails.add(new ValidationErrorDetails(ELEMENT_NOT_FOUND, request.getMaterialId().toString()));

        if(buyerRepo.findById(request.getBuyerId()).isEmpty())
            errorDetails.add(new ValidationErrorDetails(ELEMENT_NOT_FOUND, request.getBuyerId().toString()));

        if(Objects.isNull(request.getPrice())) {
            errorDetails.add(new ValidationErrorDetails(PRICE_FIELD, NULL_ERROR_MSG));
        }else if(Objects.isNull(request.getPrice().getCurrency()) || Objects.isNull(request.getPrice().getAmount())) {
            errorDetails.add(new ValidationErrorDetails(PRICE_FIELD, NULL_ERROR_MSG));
        }

        if(Objects.isNull(request.getCenterPrice())) {
            errorDetails.add(new ValidationErrorDetails(PRICE_FIELD, NULL_ERROR_MSG));
        }else if(Objects.isNull(request.getCenterPrice().getCurrency()) || Objects.isNull(request.getCenterPrice().getAmount())) {
            errorDetails.add(new ValidationErrorDetails(PRICE_FIELD, NULL_ERROR_MSG));
        }

        if(Objects.isNull(request.getAmount()))
            errorDetails.add(new ValidationErrorDetails(AMOUNT_FIELD, NULL_ERROR_MSG));

        if(Objects.isNull(request.getStatus()))
            errorDetails.add(new ValidationErrorDetails(STATUS_FIELD, NULL_ERROR_MSG));


        ExceptionThrower.throwIfNotEmpty(errorDetails);

    }
}
