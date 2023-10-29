package org.example.mappers;

import org.example.entities.PartitionEntity;
import org.example.model.Partition;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface PartitionMapper {

    @Mapping(target = "price.currency", source ="priceCurrency")
    @Mapping(target = "price.amount", source ="priceAmount")
    @Mapping(target = "transportation", source = "transportationEntity")
    Partition entityToDomain(PartitionEntity partition);

    @Mapping(source = "price.currency", target ="priceCurrency")
    @Mapping(source = "price.amount", target ="priceAmount")
    @Mapping(source = "transportation", target = "transportationEntity")
    PartitionEntity domainToEntity(Partition partition);
}
