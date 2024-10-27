package org.example.useCases.update;

import org.example.contract.repository.PaidToParentRepo;
import org.example.contract.request.update.UpdatePaidToParentRequest;
import org.example.contract.response.PaidToParentResponse;
import org.example.mappers.PaidToParentDomainMapper;
import org.example.model.PaidToParent;
import org.example.validators.update.UpdatePaidToParentValidator;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.NoSuchElementException;

public class UpdatePaidToParent {
    private final UpdatePaidToParentValidator validator;
    private final PaidToParentDomainMapper mapper;
    private final PaidToParentRepo repo;

    public UpdatePaidToParent(UpdatePaidToParentValidator validator, PaidToParentDomainMapper mapper, PaidToParentRepo repo) {
        this.validator = validator;
        this.mapper = mapper;
        this.repo = repo;
    }

    public PaidToParentResponse update(UpdatePaidToParentRequest request){
        validator.validate(request);
        final PaidToParent original = repo.findById(request.getId()).orElseThrow(NoSuchElementException::new);
        final PaidToParent paidToParent = mapper.requestToDomain(request);
        paidToParent.setCreatedAt(original.getCreatedAt());
        paidToParent.setUpdatedAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        final PaidToParent save = repo.save(paidToParent);
        return mapper.domainToResponse(save);
    }
}
