package org.example.useCases.update;

import org.example.contract.repository.OfficePaymentRepo;
import org.example.contract.request.update.UpdateOfficePaymentRequest;
import org.example.contract.response.OfficePaymentResponse;
import org.example.mappers.OfficePaymentDomainMapper;
import org.example.model.OfficePayment;
import org.example.validators.update.UpdateOfficePaymentValidator;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.NoSuchElementException;

public class UpdateOfficePayment {
    private final OfficePaymentRepo repo;
    private final UpdateOfficePaymentValidator validator;
    private final OfficePaymentDomainMapper mapper;

    public UpdateOfficePayment(OfficePaymentRepo repo, UpdateOfficePaymentValidator validator, OfficePaymentDomainMapper mapper) {
        this.repo = repo;
        this.validator = validator;
        this.mapper = mapper;
    }

    public OfficePaymentResponse update(UpdateOfficePaymentRequest request){
        validator.validate(request);
        final OfficePayment original = repo.findById(request.getId()).orElseThrow(NoSuchElementException::new);
        final OfficePayment officePayment = mapper.requestToDomain(request);
        officePayment.setCreatedAt(original.getCreatedAt());
        officePayment.setUpdatedAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        final OfficePayment save = repo.save(officePayment);
        return mapper.domainToResponse(save);
    }
}
