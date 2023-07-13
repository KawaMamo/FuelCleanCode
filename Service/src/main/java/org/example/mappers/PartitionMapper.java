package org.example.mappers;

import org.example.entities.PartitionEntity;
import org.example.model.Partition;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface PartitionMapper {
    @Mapping(target = "material.id", source ="materialId")
    @Mapping(target = "gasStation.id", source ="gasStationId")
    @Mapping(target = "price.currency", source ="priceCurrency")
    @Mapping(target = "price.amount", source ="priceAmount")
    @Mapping(target = "transportation.id", source ="transportationId")
    Partition entityToDomain(PartitionEntity partition);
    @Mapping(source = "material.id", target ="materialId")
    @Mapping(source = "gasStation.id", target ="gasStationId")
    @Mapping(source = "price.currency", target ="priceCurrency")
    @Mapping(source = "price.amount", target ="priceAmount")
    @Mapping(source = "transportation.id", target ="transportationId")
    PartitionEntity domainToEntity(Partition partition);
}
