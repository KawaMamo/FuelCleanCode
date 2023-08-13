package org.example.useCases.create;

import org.example.contract.repository.PriceCategoryRepo;
import org.example.contract.request.create.CreatePriceCategoryRequest;
import org.example.contract.response.PriceCategoryResponse;
import org.example.mappers.PriceCategoryDomainMapper;
import org.example.model.PriceCategory;
import org.example.validators.create.CreatePriceCategoryValidator;

import java.time.LocalDateTime;

public class CreatePriceCategory {
    private final CreatePriceCategoryValidator validator;
    private final PriceCategoryDomainMapper mapper;
    private final PriceCategoryRepo priceCategoryRepo;

    public CreatePriceCategory(CreatePriceCategoryValidator validator, PriceCategoryDomainMapper mapper, PriceCategoryRepo priceCategoryRepo) {
        this.validator = validator;
        this.mapper = mapper;
        this.priceCategoryRepo = priceCategoryRepo;
    }

    public PriceCategoryResponse execute(CreatePriceCategoryRequest request){
        validator.validate(request);
        final PriceCategory priceCategory = mapper.requestToDomain(request);
        priceCategory.setCreatedAt(LocalDateTime.now());
        final PriceCategory save = priceCategoryRepo.save(priceCategory);
        return mapper.domainToResponse(save);
    }
}
