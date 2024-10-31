package org.example.useCases.delete;

import org.example.contract.repository.SellerRepo;
import org.example.contract.response.SellerResponse;
import org.example.mappers.SellerDomainMapper;
import org.example.model.Seller;

import java.util.NoSuchElementException;

public class DeleteSeller {
    private final SellerRepo repo;
    private final SellerDomainMapper mapper;

    public DeleteSeller(SellerRepo repo, SellerDomainMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public SellerResponse execute(Long id){
        final Seller seller = repo.findById(id).orElseThrow(NoSuchElementException::new);
        repo.delete(seller);
        return mapper.domainToResponse(seller);
    }
}
