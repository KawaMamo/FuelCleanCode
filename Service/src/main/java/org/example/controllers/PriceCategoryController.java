package org.example.controllers;

import org.example.contract.request.CreatePriceCategoryRequest;
import org.example.contract.response.PriceCategoryResponse;
import org.example.useCases.CreatePriceCategory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/vi/price-category")
public class PriceCategoryController {
    private final CreatePriceCategory createPriceCategory;

    public PriceCategoryController(CreatePriceCategory createPriceCategory) {
        this.createPriceCategory = createPriceCategory;
    }
    @PostMapping
    public PriceCategoryResponse execute(@RequestBody CreatePriceCategoryRequest request){
        return createPriceCategory.execute(request);
    }
}
