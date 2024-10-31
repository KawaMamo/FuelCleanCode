package org.example.useCases.update;

import org.example.contract.repository.SellerRepo;
import org.example.contract.request.update.UpdateSellerRequest;
import org.example.contract.response.SellerResponse;
import org.example.mappers.SellerDomainMapper;
import org.example.model.Seller;
import org.example.validators.update.UpdateSellerValidator;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class UpdateSeller {
    private final UpdateSellerValidator validator;
    private final SellerDomainMapper mapper;
    private final SellerRepo repo;

    public UpdateSeller(UpdateSellerValidator validator, SellerDomainMapper mapper, SellerRepo repo) {
        this.validator = validator;
        this.mapper = mapper;
        this.repo = repo;
    }

    public SellerResponse execute(UpdateSellerRequest request){
        validator.validate(request);
        final Seller seller = mapper.requestToDomain(request);
        seller.setCreatedAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        final Seller save = repo.save(seller);
        return mapper.domainToResponse(save);
    }
}
