package org.example.useCases.update;

import org.example.contract.repository.CategoryRepo;
import org.example.contract.repository.MaterialRepo;
import org.example.contract.repository.PriceCategoryRepo;
import org.example.contract.request.update.UpdateCategoryRequest;
import org.example.contract.response.CategoryResponse;
import org.example.mappers.CategoryDomainMapper;
import org.example.model.Category;
import org.example.validators.update.UpdateCategoryValidator;

import java.time.LocalDateTime;

public class UpdateCategory {

    private final UpdateCategoryValidator validator;
    private final CategoryDomainMapper mapper;
    private final CategoryRepo categoryRepo;
    private final PriceCategoryRepo priceCategoryRepo;
    private final MaterialRepo materialRepo;

    public UpdateCategory(UpdateCategoryValidator validator,
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

    public CategoryResponse execute(UpdateCategoryRequest request){
        Category original = categoryRepo.findById(request.getId());
        validator.validate(request);
        final Category category = mapper.requestToDomain(request);
        category.setUpdatedAt(LocalDateTime.now());
        category.setCreatedAt(original.getCreatedAt());
        final Category update = categoryRepo.save(category);
        priceCategoryRepo.findById(update.getId()).ifPresent(update::setPriceCategory);
        materialRepo.findById(update.getId()).ifPresent(update::setMaterial);
        return mapper.domainToResponse(update);
    }
}
