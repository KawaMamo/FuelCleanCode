package org.example.mappers;

import org.example.contract.request.CreateRefineryRequest;
import org.example.contract.response.CreateRefineryResponse;
import org.example.model.Refinery;
import org.mapstruct.Mapper;

@Mapper
public interface RefineryDomainMapper {
    Refinery toDomain(CreateRefineryRequest request);
    CreateRefineryResponse toResponse(Refinery refinery);
}
