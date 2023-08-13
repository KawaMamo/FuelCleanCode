package org.example.mappers;

import org.example.contract.request.create.CreatePartitionRequest;
import org.example.contract.request.update.UpdatePartitionRequest;
import org.example.contract.response.PartitionResponse;
import org.example.model.Partition;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface PartitionDomainMapper {
    @Mapping(source = "materialId", target = "material.id")
    @Mapping(source = "gasStationId", target = "gasStation.id")
    @Mapping(source = "transportationId", target = "transportation.id")
    Partition requestToDomain(CreatePartitionRequest request);
    PartitionResponse domainToResponse(Partition partition);
    @Mapping(source = "materialId", target = "material.id")
    @Mapping(source = "gasStationId", target = "gasStation.id")
    @Mapping(source = "transportationId", target = "transportation.id")
    Partition requestToDomain(UpdatePartitionRequest request);
}
