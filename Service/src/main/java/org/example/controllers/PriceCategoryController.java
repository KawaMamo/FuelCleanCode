package org.example.controllers;

import org.example.contract.request.create.CreatePriceCategoryRequest;
import org.example.contract.request.update.UpdatePriceCategoryRequest;
import org.example.contract.response.PriceCategoryResponse;
import org.example.entities.PriceCategoryEntity;
import org.example.mappers.PriceCategoryMapper;
import org.example.model.PriceCategory;
import org.example.repositories.PriceCategoryRepository;
import org.example.specifications.CriteriaArrayToList;
import org.example.specifications.FilterSpecifications;
import org.example.specifications.SearchCriteria;
import org.example.specifications.SearchFilter;
import org.example.useCases.create.CreatePriceCategory;
import org.example.useCases.delete.DeletePriceCategory;
import org.example.useCases.update.UpdatePriceCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/price-category")
public class PriceCategoryController {
    private final CreatePriceCategory createPriceCategory;
    private final PriceCategoryRepository priceCategoryRepository;
    private final PriceCategoryMapper priceCategoryMapper;
    private final PagedResourcesAssembler assembler;
    private final UpdatePriceCategory updatePriceCategory;
    private final DeletePriceCategory deletePriceCategory;

    public PriceCategoryController(CreatePriceCategory createPriceCategory,
                                   PriceCategoryRepository priceCategoryRepository,
                                   PriceCategoryMapper priceCategoryMapper,
                                   PagedResourcesAssembler assembler,
                                   UpdatePriceCategory updatePriceCategory, DeletePriceCategory deletePriceCategory) {
        this.createPriceCategory = createPriceCategory;
        this.priceCategoryRepository = priceCategoryRepository;
        this.priceCategoryMapper = priceCategoryMapper;
        this.assembler = assembler;
        this.updatePriceCategory = updatePriceCategory;
        this.deletePriceCategory = deletePriceCategory;
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

    @PatchMapping
    public ResponseEntity<PriceCategoryResponse> updatePriceCategory(@RequestBody UpdatePriceCategoryRequest request){
        return ResponseEntity.ok(updatePriceCategory.execute(request));
    }

    @DeleteMapping("/{id}")
    public PriceCategoryResponse delete(@PathVariable Long id){
        return deletePriceCategory.execute(id);
    }
}
