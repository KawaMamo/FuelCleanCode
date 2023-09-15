package org.example.validators.update;

import org.example.contract.repository.OfficeRepo;
import org.example.contract.repository.PersonRepo;
import org.example.contract.repository.TrafficCenterRepo;
import org.example.contract.request.update.UpdateVehicleRequest;
import org.example.exceptions.ValidationErrorDetails;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static org.example.contract.constant.DomainConstant.*;
import static org.example.contract.constant.DomainConstant.ELEMENT_NOT_FOUND;

public class UpdateVehicleValidator {

    private final PersonRepo personRepo;
    private final OfficeRepo officeRepo;
    private final TrafficCenterRepo trafficCenterRepo;

    public UpdateVehicleValidator(PersonRepo personRepo, OfficeRepo officeRepo, TrafficCenterRepo trafficCenterRepo) {
        this.personRepo = personRepo;
        this.officeRepo = officeRepo;
        this.trafficCenterRepo = trafficCenterRepo;
    }

    public void validate(UpdateVehicleRequest request){
        Set<ValidationErrorDetails> validationErrorDetails = new HashSet<>();

        if(Objects.isNull(request.getId())){
            validationErrorDetails.add(new ValidationErrorDetails(ID_FIELD, NULL_ERROR_MSG));
        }

        if(Objects.isNull(request.getPlateNumber())){
            validationErrorDetails.add(new ValidationErrorDetails(PLATE_NUMBER_FIELD, NULL_ERROR_MSG));
        }

        if(request.getOffice_id()<=0){
            validationErrorDetails.add(new ValidationErrorDetails(OFFICE_FIELD, ILLEGAL_VALUE));
        }

        if(request.getDriver_id()<=0){
            validationErrorDetails.add(new ValidationErrorDetails(DRIVER_FIELD, ILLEGAL_VALUE));
        }

        if(request.getSize()<=0){
            validationErrorDetails.add(new ValidationErrorDetails(SIZE_FIELD, ILLEGAL_VALUE));
        }

        if(Objects.isNull(request.getTrafficCenter_id()) || request.getTrafficCenter_id()<=0){
            validationErrorDetails.add(new ValidationErrorDetails(TRAFFIC_CENTER, ILLEGAL_VALUE));
        }

        if(Objects.nonNull(request.getTrafficCenter_id()) && trafficCenterRepo.findById(request.getTrafficCenter_id()).isEmpty()){
            validationErrorDetails.add(new ValidationErrorDetails(TRAFFIC_CENTER, ELEMENT_NOT_FOUND));
        }

        if(officeRepo.findById(request.getOffice_id()).isEmpty()){
            validationErrorDetails.add(new ValidationErrorDetails(OFFICE_FIELD, ELEMENT_NOT_FOUND));
        }

        if(personRepo.findById(request.getDriver_id()).isEmpty()){
            validationErrorDetails.add(new ValidationErrorDetails(OFFICE_FIELD, ELEMENT_NOT_FOUND));
        }

        ExceptionThrower.throwIfNotEmpty(validationErrorDetails);

    }
}
