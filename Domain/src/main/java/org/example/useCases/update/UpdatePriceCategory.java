package org.example.useCases.update;

import org.example.contract.repository.PriceCategoryRepo;
import org.example.contract.request.update.UpdatePriceCategoryRequest;
import org.example.contract.response.PriceCategoryResponse;
import org.example.mappers.PriceCategoryDomainMapper;
import org.example.model.PriceCategory;
import org.example.validators.update.UpdatePriceCategoryValidator;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.NoSuchElementException;

public class UpdatePriceCategory {
    private final UpdatePriceCategoryValidator validator;
    private final PriceCategoryDomainMapper mapper;
    private final PriceCategoryRepo priceCategoryRepo;

    public UpdatePriceCategory(UpdatePriceCategoryValidator validator,
                               PriceCategoryDomainMapper mapper,
                               PriceCategoryRepo priceCategoryRepo) {
        this.validator = validator;
        this.mapper = mapper;
        this.priceCategoryRepo = priceCategoryRepo;
    }

    public PriceCategoryResponse execute(UpdatePriceCategoryRequest request){
        final PriceCategory original = priceCategoryRepo.findById(request.getId()).orElseThrow(NoSuchElementException::new);
        validator.validate(request);
        final PriceCategory priceCategory = mapper.requestToDomain(request);
        priceCategory.setCreatedAt(original.getCreatedAt());
        priceCategory.setUpdatedAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        final PriceCategory save = priceCategoryRepo.save(priceCategory);
        return mapper.domainToResponse(save);
    }
}
