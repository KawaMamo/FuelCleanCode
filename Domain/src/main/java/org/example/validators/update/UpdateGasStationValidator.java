package org.example.validators.update;

import org.example.contract.repository.GroupRepo;
import org.example.contract.repository.PersonRepo;
import org.example.contract.repository.PriceCategoryRepo;
import org.example.contract.repository.RegionRepo;
import org.example.contract.request.update.UpdateGasStationRequest;
import org.example.exceptions.ValidationErrorDetails;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static org.example.contract.constant.DomainConstant.*;
import static org.example.contract.constant.DomainConstant.ELEMENT_NOT_FOUND;

public class UpdateGasStationValidator {

    private final PriceCategoryRepo priceCategoryRepo;
    private final RegionRepo regionRepo;
    private final GroupRepo groupRepo;
    private final PersonRepo personRepo;

    public UpdateGasStationValidator(PriceCategoryRepo priceCategoryRepo,
                                     RegionRepo regionRepo,
                                     GroupRepo groupRepo,
                                     PersonRepo personRepo) {
        this.priceCategoryRepo = priceCategoryRepo;
        this.regionRepo = regionRepo;
        this.groupRepo = groupRepo;
        this.personRepo = personRepo;
    }

    public void validate(UpdateGasStationRequest request){
        Set<ValidationErrorDetails> errorDetails = new HashSet<>();

        if(Objects.isNull(request.getId())){
            errorDetails.add(new ValidationErrorDetails(ID_FIELD, NULL_ERROR_MSG));
        }

        if(Objects.isNull(request.getName())){
            errorDetails.add(new ValidationErrorDetails(NAME_FIELD, NULL_ERROR_MSG));
        }

        if(Objects.isNull(request.getDebtLimit())){
            errorDetails.add(new ValidationErrorDetails(DEBT_LIMIT, NULL_ERROR_MSG));
        }

        if(Objects.isNull(request.getPriceCategoryId()) || priceCategoryRepo.findById(request.getPriceCategoryId()).isEmpty()){
            errorDetails.add(new ValidationErrorDetails(PRICE_CATEGORY_FIELD, ELEMENT_NOT_FOUND));
        }

        if(Objects.isNull(request.getOwnerId()) || personRepo.findById(request.getOwnerId()).isEmpty()){
            errorDetails.add(new ValidationErrorDetails(OWNER_FIELD, ELEMENT_NOT_FOUND));
        }

        if(Objects.isNull(request.getGroupId()) || groupRepo.findById(request.getGroupId()).isEmpty()){
            errorDetails.add(new ValidationErrorDetails(GROUP_FIELD, ELEMENT_NOT_FOUND));
        }

        if(Objects.isNull(request.getRegionId()) || regionRepo.findById(request.getRegionId()).isEmpty()){
            errorDetails.add(new ValidationErrorDetails(REGION_FIELD, ELEMENT_NOT_FOUND));
        }

        ExceptionThrower.throwIfNotEmpty(errorDetails);
    }
}
