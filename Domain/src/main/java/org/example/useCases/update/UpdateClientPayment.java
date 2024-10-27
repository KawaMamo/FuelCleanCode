package org.example.useCases.update;

import org.example.contract.repository.ClientPaymentRepo;
import org.example.contract.request.update.UpdateClientPaymentRequest;
import org.example.contract.response.ClientPaymentResponse;
import org.example.mappers.ClientPaymentDomainMapper;
import org.example.model.ClientPayment;
import org.example.validators.update.UpdateClientPaymentValidator;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.NoSuchElementException;

public class UpdateClientPayment {
    private final ClientPaymentRepo repo;
    public final ClientPaymentDomainMapper mapper;
    private final UpdateClientPaymentValidator validator;

    public UpdateClientPayment(ClientPaymentRepo repo, ClientPaymentDomainMapper mapper, UpdateClientPaymentValidator validator) {
        this.repo = repo;
        this.mapper = mapper;
        this.validator = validator;
    }

    public ClientPaymentResponse update(UpdateClientPaymentRequest request){
        validator.validate(request);
        final ClientPayment original = repo.findById(request.getId()).orElseThrow(NoSuchElementException::new);
        final ClientPayment clientPayment = mapper.requestToDomain(request);
        clientPayment.setCreatedAt(original.getCreatedAt());
        clientPayment.setUpdatedAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        final ClientPayment save = repo.save(clientPayment);
        return mapper.domainToResponse(save);
    }
}
