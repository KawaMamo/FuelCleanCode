package org.example.mappers;

import org.example.contract.request.create.CreateRefineryRequest;
import org.example.contract.request.update.UpdateRefineryRequest;
import org.example.contract.response.RefineryResponse;
import org.example.model.Refinery;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface RefineryDomainMapper {
    @Mapping(source = "regionId", target = "region.id")
    Refinery toDomain(CreateRefineryRequest request);
    RefineryResponse toResponse(Refinery refinery);
    @Mapping(source = "regionId", target = "region.id")
    Refinery toDomain(UpdateRefineryRequest request);
}
