package org.example.mappers;

import org.example.entities.ForfeitEntity;
import org.example.model.Forfeit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ForfeitMapper {
    @Mapping(source = "priceAmount", target = "price.amount")
    @Mapping(source = "priceCurrency", target = "price.currency")
    Forfeit entityToDomain(ForfeitEntity forfeit);
    @Mapping(target = "priceAmount", source = "price.amount")
    @Mapping(target = "priceCurrency", source = "price.currency")
    ForfeitEntity domainToEntity(Forfeit forfeit);
}
