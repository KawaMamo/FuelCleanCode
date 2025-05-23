package org.example.mappers;

import org.example.contract.request.create.CreateRegionRequest;
import org.example.contract.request.update.UpdateRegionRequest;
import org.example.contract.response.RegionResponse;
import org.example.model.Region;
import org.mapstruct.Mapper;

@Mapper
public interface RegionDomainMapper {
    Region requestToDomain(CreateRegionRequest request);
    RegionResponse domainToResponse(Region region);
    Region requestToDomain(UpdateRegionRequest request);
}
