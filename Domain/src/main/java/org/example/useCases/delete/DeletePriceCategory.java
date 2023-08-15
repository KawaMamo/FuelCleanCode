package org.example.useCases.delete;

import org.example.contract.repository.PriceCategoryRepo;
import org.example.contract.response.PriceCategoryResponse;
import org.example.mappers.PriceCategoryDomainMapper;
import org.example.model.PriceCategory;

import java.util.NoSuchElementException;

public class DeletePriceCategory {
    private final PriceCategoryRepo priceCategoryRepo;
    private final PriceCategoryDomainMapper mapper;
    public DeletePriceCategory(PriceCategoryRepo priceCategoryRepo, PriceCategoryDomainMapper mapper) {
        this.priceCategoryRepo = priceCategoryRepo;
        this.mapper = mapper;
    }
    public PriceCategoryResponse execute(Long id){
        final PriceCategory priceCategory = priceCategoryRepo.findById(id).orElseThrow(NoSuchElementException::new);
        priceCategoryRepo.delete(priceCategory);
        return mapper.domainToResponse(priceCategory);
    }
}
