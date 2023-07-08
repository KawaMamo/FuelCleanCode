package org.example.adapters;

import org.example.contract.repository.CategoryRepo;
import org.example.mappers.CategoryMapper;
import org.example.model.Category;
import org.example.repositories.CategoryRepository;
import org.example.entities.CategoryEntity;

public class CategoryAdapter implements CategoryRepo {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper mapper;

    public CategoryAdapter(CategoryRepository categoryRepository, CategoryMapper mapper) {
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
    }

    @Override
    public Category save(Category category) {
        final CategoryEntity categoryEntity = mapper.domainToEntity(category);
        final CategoryEntity save = categoryRepository.save(categoryEntity);
        return mapper.entityToDomain(save);
    }
}
