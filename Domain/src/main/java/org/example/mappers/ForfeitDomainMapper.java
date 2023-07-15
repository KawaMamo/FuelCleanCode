package org.example.mappers;

import org.example.contract.request.CreateForfeitRequest;
import org.example.contract.response.ForfeitResponse;
import org.example.model.Forfeit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ForfeitDomainMapper {
    @Mapping(source = "vehiclesId", target = "vehicles.id")
    @Mapping(source = "partitionId", target = "partition.id")
    Forfeit requestToDomain(CreateForfeitRequest request);

    ForfeitResponse domainToResponse(Forfeit forfeit);
}
