package org.example.mappers;

import org.example.contract.request.CreateCategoryRequest;
import org.example.contract.response.CategoryResponse;
import org.example.model.Category;
import org.mapstruct.Mapper;

@Mapper
public interface CategoryDomainMapper {
    Category requestToDomain(CreateCategoryRequest request);
    CategoryResponse domainToResponse(Category category);
}
