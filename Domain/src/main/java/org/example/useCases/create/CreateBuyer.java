package org.example.useCases.create;

import org.example.contract.repository.BuyerRepo;
import org.example.contract.request.create.CreateBuyerRequest;
import org.example.contract.response.BuyerResponse;
import org.example.mappers.BuyerDomainMapper;
import org.example.model.Buyer;
import org.example.validators.create.CreateBuyerValidator;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class CreateBuyer {
    private final CreateBuyerValidator validator;
    private final BuyerDomainMapper mapper;
    private final BuyerRepo repo;

    public CreateBuyer(CreateBuyerValidator validator, BuyerDomainMapper mapper, BuyerRepo repo) {
        this.validator = validator;
        this.mapper = mapper;
        this.repo = repo;
    }

    public BuyerResponse createBuyer(CreateBuyerRequest request){
        validator.validate(request);
        final Buyer buyer = mapper.requestToDomain(request);
        buyer.setCreatedAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        final Buyer save = repo.save(buyer);
        return mapper.domainToResponse(save);
    }
}
