package org.example.mappers;

import org.example.contract.request.create.CreatePriceCategoryRequest;
import org.example.contract.request.update.UpdatePriceCategoryRequest;
import org.example.contract.response.PriceCategoryResponse;
import org.example.model.PriceCategory;
import org.mapstruct.Mapper;

@Mapper
public interface PriceCategoryDomainMapper {
    PriceCategory requestToDomain(CreatePriceCategoryRequest request);
    PriceCategoryResponse domainToResponse(PriceCategory priceCategory);
    PriceCategory requestToDomain(UpdatePriceCategoryRequest request);
}
