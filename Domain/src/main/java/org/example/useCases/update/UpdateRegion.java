package org.example.useCases.update;


import org.example.contract.repository.RegionRepo;
import org.example.contract.request.update.UpdateRegionRequest;
import org.example.contract.response.RegionResponse;
import org.example.mappers.RegionDomainMapper;
import org.example.model.Region;
import org.example.validators.update.UpdateRegionValidator;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

public class UpdateRegion {
  private final UpdateRegionValidator validator;
  private final RegionDomainMapper mapper;
  private final RegionRepo regionRepo;

    public UpdateRegion(UpdateRegionValidator validator,
                        RegionDomainMapper mapper,
                        RegionRepo regionRepo) {
        this.validator = validator;
        this.mapper = mapper;
        this.regionRepo = regionRepo;
    }

    public RegionResponse execute(UpdateRegionRequest request) {
        final Region original = regionRepo.findById(request.getId()).orElseThrow(NoSuchElementException::new);
        validator.Validate(request);
        Region region = mapper.requestToDomain(request);
        region.setCreatedAt(original.getCreatedAt());
        region.setUpdatedAt(LocalDateTime.now());
        regionRepo.save(region);
        return mapper.domainToResponse(region);
    }
}
