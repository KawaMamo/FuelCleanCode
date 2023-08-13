package org.example.useCases.create;

import org.example.contract.repository.RefineryRepo;
import org.example.contract.request.create.CreateRefineryRequest;
import org.example.contract.response.RefineryResponse;
import org.example.mappers.RefineryDomainMapper;
import org.example.model.Refinery;
import org.example.validators.create.CreateRefineryValidator;

import java.time.LocalDateTime;

public class CreateRefinery {
    private final CreateRefineryValidator validator;
    private final RefineryDomainMapper mapper;
    final private RefineryRepo refineryRepo;

    public CreateRefinery(CreateRefineryValidator validator, RefineryDomainMapper mapper, RefineryRepo refineryRepo) {
        this.validator = validator;
        this.mapper = mapper;
        this.refineryRepo = refineryRepo;
    }

    public RefineryResponse execute(CreateRefineryRequest request){

        validator.validate(request);
        final Refinery refinery = mapper.toDomain(request);
        refinery.setCreatedAt(LocalDateTime.now());
        final Refinery save = refineryRepo.save(refinery);
        return mapper.toResponse(save);
    }

}
