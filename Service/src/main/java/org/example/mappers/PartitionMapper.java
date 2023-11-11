package org.example.mappers;

import org.example.entities.PartitionEntity;
import org.example.entities.TransportationEntity;
import org.example.model.Partition;
import org.example.model.Transportation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(uses = TransMapper.class)
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
