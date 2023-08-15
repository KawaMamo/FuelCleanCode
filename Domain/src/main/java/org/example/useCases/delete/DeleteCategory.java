package org.example.useCases.delete;

import org.example.contract.repository.CategoryRepo;
import org.example.contract.response.CategoryResponse;
import org.example.mappers.CategoryDomainMapper;
import org.example.model.Category;

public class DeleteCategory {
    private final CategoryRepo categoryRepo;
    private final CategoryDomainMapper mapper;

    public DeleteCategory(CategoryRepo categoryRepo, CategoryDomainMapper mapper) {
        this.categoryRepo = categoryRepo;
        this.mapper = mapper;
    }

    public CategoryResponse execute(Long categoryId){
        Category category = categoryRepo.findById(categoryId);
        categoryRepo.delete(category);
        return mapper.domainToResponse(category);
    }
}
