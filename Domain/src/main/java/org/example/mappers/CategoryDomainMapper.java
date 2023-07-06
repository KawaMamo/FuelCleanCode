package org.example.mappers;

import org.example.contract.request.CreateCategoryRequest;
import org.example.contract.response.CategoryResponse;
import org.example.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface CategoryDomainMapper {
    @Mapping(source = "priceCategoryId", target = "priceCategory.id")
    @Mapping(source = "materialId", target = "material.id")
    Category requestToDomain(CreateCategoryRequest request);

    CategoryResponse domainToResponse(Category category);
}
