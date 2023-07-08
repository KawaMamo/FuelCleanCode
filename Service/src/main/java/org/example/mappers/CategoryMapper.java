package org.example.mappers;

import org.example.model.Category;
import org.example.entities.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface CategoryMapper {
    @Mapping(source = "priceCategory.id", target = "priceCategoryId")
    @Mapping(source = "material.id", target = "materialId")
    @Mapping(source = "price.currency", target = "priceCurrency")
    @Mapping(source = "price.amount", target = "priceAmount")
    CategoryEntity domainToEntity(Category category);
    @Mapping(target = "priceCategory.id", source = "priceCategoryId")
    @Mapping(target = "material.id", source = "materialId")
    @Mapping(target = "price.currency", source = "priceCurrency")
    @Mapping(target = "price.amount", source = "priceAmount")
    Category entityToDomain(CategoryEntity categoryEntity);
}
