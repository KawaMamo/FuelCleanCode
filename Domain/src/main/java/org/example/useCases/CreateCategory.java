package org.example.useCases;

import org.example.contract.repository.CategoryRepo;
import org.example.contract.repository.MaterialRepo;
import org.example.contract.repository.PriceCategoryRepo;
import org.example.contract.request.CreateCategoryRequest;
import org.example.contract.response.CategoryResponse;
import org.example.mappers.CategoryDomainMapper;
import org.example.model.Category;
import org.example.model.Material;
import org.example.model.PriceCategory;
import org.example.validators.CreateCategoryValidator;

import java.time.LocalDateTime;
import java.util.Optional;

public class CreateCategory {

    private final CreateCategoryValidator validator;
    private final CategoryDomainMapper mapper;
    private final CategoryRepo categoryRepo;
    private final PriceCategoryRepo priceCategoryRepo;
    private final MaterialRepo materialRepo;

    public CreateCategory(CreateCategoryValidator validator,
                          CategoryDomainMapper mapper,
                          CategoryRepo categoryRepo,
                          PriceCategoryRepo priceCategoryRepo,
                          MaterialRepo materialRepo) {
        this.validator = validator;
        this.mapper = mapper;
        this.categoryRepo = categoryRepo;
        this.priceCategoryRepo = priceCategoryRepo;
        this.materialRepo = materialRepo;
    }

    public CategoryResponse execute(CreateCategoryRequest request){
        validator.validate(request);
        final Category category = mapper.requestToDomain(request);
        category.setCreatedAt(LocalDateTime.now());
        final Category save = categoryRepo.save(category);
        final Optional<PriceCategory> optionalPriceCategory = priceCategoryRepo.findById(save.getPriceCategory().getId());
        final Optional<Material> material = materialRepo.findById(save.getMaterial().getId());
        material.ifPresent(save::setMaterial);
        optionalPriceCategory.ifPresent(save::setPriceCategory);
        return mapper.domainToResponse(save);
    }
}
