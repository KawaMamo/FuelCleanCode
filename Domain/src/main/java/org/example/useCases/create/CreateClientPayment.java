package org.example.useCases.create;

import org.example.contract.repository.ClientPaymentRepo;
import org.example.contract.request.create.CreateClientPaymentRequest;
import org.example.contract.response.ClientPaymentResponse;
import org.example.mappers.ClientPaymentDomainMapper;
import org.example.model.ClientPayment;
import org.example.validators.create.CreateClientPaymentValidator;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class CreateClientPayment {
    private final CreateClientPaymentValidator validator;
    private final ClientPaymentDomainMapper mapper;
    private final ClientPaymentRepo repo;

    public CreateClientPayment(CreateClientPaymentValidator validator, ClientPaymentDomainMapper mapper, ClientPaymentRepo repo) {
        this.validator = validator;
        this.mapper = mapper;
        this.repo = repo;
    }

    public ClientPaymentResponse create(CreateClientPaymentRequest request){
        validator.validate(request);
        final ClientPayment clientPayment = mapper.requestToDomain(request);
        clientPayment.setCreatedAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        final ClientPayment save = repo.save(clientPayment);
        return mapper.domainToResponse(save);
    }
}
