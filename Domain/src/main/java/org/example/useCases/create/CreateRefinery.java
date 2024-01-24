package org.example.useCases.create;

import org.example.contract.repository.RefineryRepo;
import org.example.contract.repository.RegionRepo;
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
    final private RegionRepo regionRepo;

    public CreateRefinery(CreateRefineryValidator validator, RefineryDomainMapper mapper, RefineryRepo refineryRepo, RegionRepo regionRepo) {
        this.validator = validator;
        this.mapper = mapper;
        this.refineryRepo = refineryRepo;
        this.regionRepo = regionRepo;
    }

    public RefineryResponse execute(CreateRefineryRequest request){

        validator.validate(request);
        final Refinery refinery = mapper.toDomain(request);
        refinery.setCreatedAt(LocalDateTime.now());
        final Refinery save = refineryRepo.save(refinery);
        regionRepo.findById(save.getRegion().getId()).ifPresent(save::setRegion);
        return mapper.toResponse(save);
    }

}
