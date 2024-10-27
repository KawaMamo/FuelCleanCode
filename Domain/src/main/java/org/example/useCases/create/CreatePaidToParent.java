package org.example.useCases.create;

import org.example.contract.repository.PaidToParentRepo;
import org.example.contract.request.create.CreatePaidToParentRequest;
import org.example.contract.response.PaidToParentResponse;
import org.example.mappers.PaidToParentDomainMapper;
import org.example.model.PaidToParent;
import org.example.validators.create.CreatePaidToParentValidator;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class CreatePaidToParent {
    private final CreatePaidToParentValidator validator;
    private final PaidToParentDomainMapper mapper;
    private final PaidToParentRepo repo;

    public CreatePaidToParent(CreatePaidToParentValidator validator, PaidToParentDomainMapper mapper, PaidToParentRepo repo) {
        this.validator = validator;
        this.mapper = mapper;
        this.repo = repo;
    }

    public PaidToParentResponse create(CreatePaidToParentRequest request){
        validator.validate(request);
        final PaidToParent paidToParent = mapper.requestToDomain(request);
        paidToParent.setCreatedAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        final PaidToParent save = repo.save(paidToParent);
        return mapper.domainToResponse(save);
    }
}
