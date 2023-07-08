package org.example.mappers;

import org.example.entities.TransportationEntity;
import org.example.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper
public interface TransMapper {

    @Mapping(source = "vehicle.id", target = "vehicleEntityId")
    @Mapping(source = "refinery.id", target = "refineryId")
    @Mapping(source = "document.id", target = "documentId")
    TransportationEntity domainToEntity(Transportation transportation);

    @Mapping(target = "vehicle.id", source = "vehicleEntityId")
    @Mapping(target = "refinery.id", source = "refineryId")
    @Mapping(target = "document.id", source = "documentId")
    Transportation entityToDomain(TransportationEntity transportationEntity);

    @Named("partitionToIds")
    static Long partitionToIds(Partition partition) {
        return partition.getId();
    }



}
