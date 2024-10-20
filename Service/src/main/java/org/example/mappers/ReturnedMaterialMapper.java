package org.example.mappers;

import org.example.entities.ReturnedMaterial;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ReturnedMaterialMapper {
    @Mapping(source = "price.currency", target = "priceCurrency")
    @Mapping(source = "price.amount", target = "priceAmount")
    @Mapping(source = "centerPrice.currency", target = "centerPriceCurrency")
    @Mapping(source = "centerPrice.amount", target = "centerPriceAmount")
    ReturnedMaterial domainToEntity(org.example.model.ReturnedMaterial returnedMaterial);
    @Mapping(target = "price.currency", source = "priceCurrency")
    @Mapping(target = "price.amount", source = "priceAmount")
    @Mapping(target = "centerPrice.currency", source = "centerPriceCurrency")
    @Mapping(target = "centerPrice.amount", source = "centerPriceAmount")
    org.example.model.ReturnedMaterial entityToDomain(ReturnedMaterial entity);
}
