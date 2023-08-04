package org.example.controllers;

import org.example.contract.request.CreatePriceCategoryRequest;
import org.example.contract.response.PriceCategoryResponse;
import org.example.entities.PriceCategoryEntity;
import org.example.mappers.PriceCategoryMapper;
import org.example.model.PriceCategory;
import org.example.repositories.PriceCategoryRepository;
import org.example.specifications.CriteriaArrayToList;
import org.example.specifications.FilterSpecifications;
import org.example.specifications.SearchCriteria;
import org.example.specifications.SearchFilter;
import org.example.useCases.CreatePriceCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/price-category")
public class PriceCategoryController {
    private final CreatePriceCategory createPriceCategory;
    private final PriceCategoryRepository priceCategoryRepository;
    private final PriceCategoryMapper priceCategoryMapper;
    private final PagedResourcesAssembler assembler;

    public PriceCategoryController(CreatePriceCategory createPriceCategory,
                                   PriceCategoryRepository priceCategoryRepository,
                                   PriceCategoryMapper priceCategoryMapper,
                                   PagedResourcesAssembler assembler) {
        this.createPriceCategory = createPriceCategory;
        this.priceCategoryRepository = priceCategoryRepository;
        this.priceCategoryMapper = priceCategoryMapper;
        this.assembler = assembler;
    }

    @PostMapping
    public PriceCategoryResponse execute(@RequestBody CreatePriceCategoryRequest request){
        return createPriceCategory.execute(request);
    }

    @GetMapping
    public PagedModel<PriceCategoryResponse> listPriceCategories(SearchFilter filter, Pageable pageable){
        final List<SearchCriteria> criteriaList = CriteriaArrayToList.getCriteriaList(filter);
        final FilterSpecifications<PriceCategoryEntity> specifications = new FilterSpecifications<>(criteriaList);
        final Page<PriceCategory> page = priceCategoryRepository.findAll(specifications, pageable).map(priceCategoryMapper::entityToDomain);
        return assembler.toModel(page);
    }
}
