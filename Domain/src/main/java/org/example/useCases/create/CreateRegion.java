package org.example.useCases.create;

import org.example.contract.repository.RegionRepo;
import org.example.contract.request.create.CreateRegionRequest;
import org.example.contract.response.RegionResponse;
import org.example.mappers.RegionDomainMapper;
import org.example.model.Region;
import org.example.validators.create.CreateRegionValidator;

import java.time.LocalDateTime;

public class CreateRegion {
    private final CreateRegionValidator validator;
    private final RegionDomainMapper mapper;
    private final RegionRepo regionRepo;

    public CreateRegion(CreateRegionValidator validator, RegionDomainMapper mapper, RegionRepo regionRepo) {
        this.validator = validator;
        this.mapper = mapper;
        this.regionRepo = regionRepo;
    }

    public RegionResponse execute(CreateRegionRequest request){
        validator.Validate(request);
        final Region region = mapper.requestToDomain(request);
        region.setCreatedAt(LocalDateTime.now());
        final Region save = regionRepo.save(region);
        return mapper.domainToResponse(save);
    }
}
