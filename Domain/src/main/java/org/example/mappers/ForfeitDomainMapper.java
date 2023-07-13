package org.example.mappers;

import org.example.contract.request.CreateForfeitRequest;
import org.example.contract.response.ForfeitResponse;
import org.example.model.Forfeit;
import org.mapstruct.Mapper;

@Mapper
public interface ForfeitDomainMapper {
    Forfeit requestToDomain(CreateForfeitRequest request);
    ForfeitResponse domainToResponse(Forfeit forfeit);
}
