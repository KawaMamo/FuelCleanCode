package org.example.repositories;

import org.example.contract.response.CategoryResponse;
import org.example.entities.CategoryEntity;
import org.example.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long>,
        PagingAndSortingRepository<CategoryEntity, Long>, JpaSpecificationExecutor<CategoryEntity> {
}
