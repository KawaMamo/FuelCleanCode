package org.example.mappers;

import org.example.model.PriceCategory;
import org.example.repositories.entities.PriceCategoryEntity;
import org.mapstruct.Mapper;

@Mapper
public interface PriceCategoryMapper {
    PriceCategoryEntity domainToEntity(PriceCategory priceCategory);
    PriceCategory entityToDomain(PriceCategoryEntity priceCategory);
}
