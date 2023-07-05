package org.example.useCases;

import org.example.contract.repository.CategoryRepo;
import org.example.contract.request.CreateCategoryRequest;
import org.example.contract.response.CategoryResponse;
import org.example.mappers.CategoryDomainMapper;
import org.example.model.Category;
import org.example.validators.CreateCategoryValidator;

import java.time.LocalDateTime;

public class CreateCategory {

    private final CreateCategoryValidator validator;
    private final CategoryDomainMapper mapper;
    private final CategoryRepo categoryRepo;


    public CreateCategory(CreateCategoryValidator validator, CategoryDomainMapper mapper, CategoryRepo categoryRepo) {
        this.validator = validator;
        this.mapper = mapper;
        this.categoryRepo = categoryRepo;
    }

    public CategoryResponse execute(CreateCategoryRequest request){
        validator.validate(request);
        final Category category = mapper.requestToDomain(request);
        category.setCreatedAt(LocalDateTime.now());
        final Category save = categoryRepo.save(category);
        return mapper.domainToResponse(save);
    }
}
