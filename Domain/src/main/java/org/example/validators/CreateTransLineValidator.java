package org.example.validators;

import org.example.contract.repository.GasStationRepo;
import org.example.contract.repository.RefineryRepo;
import org.example.contract.request.CreateTransLineRequest;
import org.example.exceptions.DomainValidationException;
import org.example.exceptions.ValidationErrorDetails;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static org.example.contract.constant.DomainConstant.*;

public class CreateTransLineValidator {

    public final GasStationRepo gasStationRepo;
    private final RefineryRepo refineryRepo;

    public CreateTransLineValidator(GasStationRepo gasStationRepo, RefineryRepo refineryRepo) {
        this.gasStationRepo = gasStationRepo;

        this.refineryRepo = refineryRepo;
    }

    public void validate(CreateTransLineRequest request){
        Set<ValidationErrorDetails> errorDetails = new HashSet<>();

        if (gasStationRepo.findById(request.getDestinationId()).isEmpty() && refineryRepo.findById(request.getDestinationId()).isEmpty()) {
            errorDetails.add(new ValidationErrorDetails(DESTINATION_FIELD, NULL_ERROR_MSG));
        }

        if (refineryRepo.findById(request.getSourceId()).isEmpty() && gasStationRepo.findById(request.getSourceId()).isEmpty()) {
            errorDetails.add(new ValidationErrorDetails(SOURCE_FIELD, NULL_ERROR_MSG));
        }

        if(Objects.isNull(request.getFeeAmount())){
            errorDetails.add(new ValidationErrorDetails(FEE_FIELD, NULL_ERROR_MSG));
        }

        if (Objects.isNull(request.getFeeCurrency())) {
            errorDetails.add(new ValidationErrorDetails(Currency_FIELD, NULL_ERROR_MSG));
        }

        if(!errorDetails.isEmpty()){
            throw new DomainValidationException(errorDetails);
        }
    }
}
