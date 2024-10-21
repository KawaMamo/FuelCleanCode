package org.example.useCases.delete;

import org.example.contract.repository.BuyerRepo;
import org.example.contract.response.BuyerResponse;
import org.example.mappers.BuyerDomainMapper;
import org.example.model.Buyer;

import java.util.NoSuchElementException;

public class DeleteBuyer {
    private final BuyerRepo repo;
    private final BuyerDomainMapper mapper;

    public DeleteBuyer(BuyerRepo repo, BuyerDomainMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public BuyerResponse delete(Long id) {
        final Buyer buyer = repo.findById(id).orElseThrow(NoSuchElementException::new);
        repo.delete(buyer);
        return mapper.domainToResponse(buyer);
    }
}
