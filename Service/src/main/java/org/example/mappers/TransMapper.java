package org.example.mappers;

import org.example.entities.TransportationEntity;
import org.example.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper
public interface TransMapper {

    @Mapping(source = "vehicle", target = "vehicle")
    @Mapping(source = "refinery", target = "refinery")
    @Mapping(source = "document", target = "document")
    @Mapping(source = "partitions", target = "partitionEntities")
    TransportationEntity domainToEntity(Transportation transportation);

    @Mapping(target = "vehicle", source = "vehicle")
    @Mapping(target = "refinery", source = "refinery")
    @Mapping(target = "document", source = "document")
    @Mapping(target = "partitions", source = "partitionEntities")
    Transportation entityToDomain(TransportationEntity transportationEntity);

    @Named("partitionToIds")
    static Long partitionToIds(Partition partition) {
        return partition.getId();
    }



}
