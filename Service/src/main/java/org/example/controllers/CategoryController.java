package org.example.controllers;

import org.example.contract.request.CreateCategoryRequest;
import org.example.contract.response.CategoryResponse;
import org.example.useCases.CreateCategory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/category")
public class CategoryController {

    private final CreateCategory createCategoryUserCase;

    public CategoryController(CreateCategory createCategoryUserCase) {
        this.createCategoryUserCase = createCategoryUserCase;
    }

    @PostMapping
    CategoryResponse createCategory(@RequestBody CreateCategoryRequest request){
        return createCategoryUserCase.execute(request);
    }
}
