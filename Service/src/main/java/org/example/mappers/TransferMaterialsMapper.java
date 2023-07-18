package org.example.mappers;

import org.example.entities.TransferMaterialsEntity;
import org.example.model.TransferMaterials;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface TransferMaterialsMapper {
    @Mapping(source = "priceCurrency", target = "price.currency")
    @Mapping(source = "priceAmount", target = "price.amount")
    TransferMaterials entityToDomain(TransferMaterialsEntity transferMaterials);
    @Mapping(target = "priceCurrency", source = "price.currency")
    @Mapping(target = "priceAmount", source = "price.amount")
    TransferMaterialsEntity domainToEntity(TransferMaterials transferMaterials);
}
