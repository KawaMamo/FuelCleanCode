package org.example.mappers;

import org.example.contract.request.CreateTransRequest;
import org.example.contract.response.CreateTransResponse;
import org.example.model.Transportation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface DomainTransMapper {
    @Mapping(source = "refinery_id", target = "refinery.id")
    @Mapping(source = "vehicle_id", target = "vehicle.id")
    Transportation toDomain(CreateTransRequest createTransRequest);
    CreateTransResponse toResponse(Transportation transportation);

}
