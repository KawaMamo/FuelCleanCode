package org.example.useCases.create;

import org.example.contract.repository.SellerRepo;
import org.example.contract.request.create.CreateSellerRequest;
import org.example.contract.response.SellerResponse;
import org.example.mappers.SellerDomainMapper;
import org.example.model.Seller;
import org.example.validators.create.CreateSellerValidator;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class CreateSeller {
    private final CreateSellerValidator validator;
    private final SellerRepo repo;
    private final SellerDomainMapper mapper;

    public CreateSeller(CreateSellerValidator validator, SellerRepo repo, SellerDomainMapper mapper) {
        this.validator = validator;
        this.repo = repo;
        this.mapper = mapper;
    }

    public SellerResponse execute(CreateSellerRequest request){
        validator.validate(request);
        final Seller seller = mapper.requestToDomain(request);
        seller.setCreatedAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        final Seller save = repo.save(seller);
        return mapper.domainToResponse(save);
    }
}
