package org.example.mappers;

import org.example.contract.request.create.CreateRefineryRequest;
import org.example.contract.request.update.UpdateRefineryRequest;
import org.example.contract.response.RefineryResponse;
import org.example.model.Refinery;
import org.mapstruct.Mapper;

@Mapper
public interface RefineryDomainMapper {
    Refinery toDomain(CreateRefineryRequest request);
    RefineryResponse toResponse(Refinery refinery);
    Refinery toDomain(UpdateRefineryRequest request);
}
