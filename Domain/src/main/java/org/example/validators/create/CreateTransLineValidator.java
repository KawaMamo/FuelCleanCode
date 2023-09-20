package org.example.validators.create;

import org.example.contract.repository.GasStationRepo;
import org.example.contract.repository.RefineryRepo;
import org.example.contract.repository.RegionRepo;
import org.example.contract.request.create.CreateTransLineRequest;
import org.example.exceptions.ValidationErrorDetails;
import org.example.validators.update.ExceptionThrower;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static org.example.contract.constant.DomainConstant.*;

public class CreateTransLineValidator {

    public final GasStationRepo gasStationRepo;
    private final RefineryRepo refineryRepo;
    private final RegionRepo regionRepo;

    public CreateTransLineValidator(GasStationRepo gasStationRepo, RefineryRepo refineryRepo, RegionRepo regionRepo) {
        this.gasStationRepo = gasStationRepo;

        this.refineryRepo = refineryRepo;
        this.regionRepo = regionRepo;
    }

    public void validate(CreateTransLineRequest request){
        Set<ValidationErrorDetails> errorDetails = new HashSet<>();

        if (gasStationRepo.findById(request.getDestinationId()).isEmpty()
                && refineryRepo.findById(request.getDestinationId()).isEmpty()
                && regionRepo.findById(request.getDestinationId()).isEmpty()) {
            errorDetails.add(new ValidationErrorDetails(DESTINATION_FIELD, ELEMENT_NOT_FOUND));
        }

        if (refineryRepo.findById(request.getSourceId()).isEmpty()
                && gasStationRepo.findById(request.getSourceId()).isEmpty()
                && regionRepo.findById(request.getSourceId()).isEmpty()) {
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
