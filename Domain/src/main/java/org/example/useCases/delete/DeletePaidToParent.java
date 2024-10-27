package org.example.useCases.delete;

import org.example.contract.repository.PaidToParentRepo;
import org.example.contract.response.PaidToParentResponse;
import org.example.mappers.PaidToParentDomainMapper;
import org.example.model.PaidToParent;

import java.util.NoSuchElementException;

public class DeletePaidToParent {
    private final PaidToParentRepo repo;
    private final PaidToParentDomainMapper mapper;

    public DeletePaidToParent(PaidToParentRepo repo, PaidToParentDomainMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public PaidToParentResponse delete(Long id) {
        final PaidToParent paidToParent = repo.findById(id).orElseThrow(NoSuchElementException::new);
        repo.delete(paidToParent);
        return mapper.domainToResponse(paidToParent);
    }
}
