package org.example.useCases.update;

import org.example.contract.repository.BuyerRepo;
import org.example.contract.request.update.UpdateBuyerRequest;
import org.example.contract.response.BuyerResponse;
import org.example.mappers.BuyerDomainMapper;
import org.example.model.Buyer;
import org.example.validators.update.UpdateBuyerValidator;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.NoSuchElementException;
import java.util.Optional;

public class UpdateBuyer {
    private final UpdateBuyerValidator validator;
    private final BuyerDomainMapper mapper;
    private final BuyerRepo repo;

    public UpdateBuyer(UpdateBuyerValidator validator, BuyerDomainMapper mapper, BuyerRepo repo) {
        this.validator = validator;
        this.mapper = mapper;
        this.repo = repo;
    }

    public BuyerResponse updateBuyer(UpdateBuyerRequest request){
        validator.validate(request);
        final Buyer original = repo.findById(request.getId()).orElseThrow(NoSuchElementException::new);
        final Buyer buyer = mapper.requestToDomain(request);
        buyer.setUpdatedAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        buyer.setCreatedAt(original.getCreatedAt());
        final Buyer save = repo.save(buyer);
        return mapper.domainToResponse(save);
    }
}
