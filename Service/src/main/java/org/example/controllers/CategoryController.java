package org.example.controllers;

import org.example.contract.request.CreateCategoryRequest;
import org.example.contract.request.update.UpdateCategoryRequest;
import org.example.contract.response.CategoryResponse;
import org.example.entities.CategoryEntity;
import org.example.mappers.CategoryMapper;
import org.example.model.Category;
import org.example.repositories.CategoryRepository;
import org.example.specifications.CriteriaArrayToList;
import org.example.specifications.FilterSpecifications;
import org.example.specifications.SearchCriteria;
import org.example.specifications.SearchFilter;
import org.example.useCases.CreateCategory;
import org.example.useCases.update.UpdateCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/category")
public class CategoryController {

    private final CreateCategory createCategoryUserCase;
    private final CategoryRepository categoryRepository;
    private final PagedResourcesAssembler pagedResourcesAssembler;
    private final CategoryMapper categoryMapper;
    private final UpdateCategory updateCategory;

    public CategoryController(CreateCategory createCategoryUserCase,
                              CategoryRepository categoryRepository,
                              PagedResourcesAssembler pagedResourcesAssembler,
                              CategoryMapper categoryMapper, UpdateCategory updateCategory) {
        this.createCategoryUserCase = createCategoryUserCase;
        this.categoryRepository = categoryRepository;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
        this.categoryMapper = categoryMapper;
        this.updateCategory = updateCategory;
    }

    @PostMapping
    CategoryResponse createCategory(@RequestBody CreateCategoryRequest request){
        return createCategoryUserCase.execute(request);
    }

    @GetMapping
    public PagedModel<CategoryResponse> listCategories(SearchFilter searchFilter, Pageable pageable){
        final List<SearchCriteria> criteriaList = CriteriaArrayToList.getCriteriaList(searchFilter);
        final FilterSpecifications<CategoryEntity> specifications = new FilterSpecifications<>(criteriaList);
        final Page<Category> page = categoryRepository.findAll(specifications, pageable).map(categoryMapper::entityToDomain);
        return pagedResourcesAssembler.toModel(page);
    }

    @PatchMapping
    public ResponseEntity<CategoryResponse> update(@RequestBody UpdateCategoryRequest request){
        return ResponseEntity.ok(updateCategory.execute(request));
    }
}
