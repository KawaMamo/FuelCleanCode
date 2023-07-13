package org.example.useCases;

import org.example.contract.repository.ForfeitRepo;
import org.example.contract.request.CreateForfeitRequest;
import org.example.contract.response.ForfeitResponse;
import org.example.mappers.ForfeitDomainMapper;
import org.example.model.Forfeit;
import org.example.validators.CreateForfeitValidator;

import java.time.LocalDateTime;

public class CreateForfeit {
    private final ForfeitRepo forfeitRepo;
    private final ForfeitDomainMapper mapper;
    private final CreateForfeitValidator validator;

    public CreateForfeit(ForfeitRepo forfeitRepo, ForfeitDomainMapper mapper, CreateForfeitValidator validator) {
        this.forfeitRepo = forfeitRepo;
        this.mapper = mapper;
        this.validator = validator;
    }
    public ForfeitResponse execute(CreateForfeitRequest request){
        validator.validate(request);
        final Forfeit forfeit = mapper.requestToDomain(request);
        forfeit.setCreatedAt(LocalDateTime.now());
        final Forfeit save = forfeitRepo.save(forfeit);
        return mapper.domainToResponse(save);
    }
}
