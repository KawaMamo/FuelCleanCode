package org.example.useCases.create;

import org.example.contract.repository.OfficePaymentRepo;
import org.example.contract.request.create.CreateOfficePaymentRequest;
import org.example.contract.response.OfficePaymentResponse;
import org.example.mappers.OfficePaymentDomainMapper;
import org.example.model.OfficePayment;
import org.example.validators.create.CreateOfficePaymentValidator;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class CreateOfficePayment {
    private final CreateOfficePaymentValidator validator;
    private final OfficePaymentRepo repo;
    public final OfficePaymentDomainMapper mapper;

    public CreateOfficePayment(CreateOfficePaymentValidator validator, OfficePaymentRepo repo, OfficePaymentDomainMapper mapper) {
        this.validator = validator;
        this.repo = repo;
        this.mapper = mapper;
    }

    public OfficePaymentResponse create(CreateOfficePaymentRequest request){
        validator.validate(request);
        final OfficePayment officePayment = mapper.requestToDomain(request);
        officePayment.setCreatedAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        final OfficePayment save = repo.save(officePayment);
        return mapper.domainToResponse(save);
    }
}
