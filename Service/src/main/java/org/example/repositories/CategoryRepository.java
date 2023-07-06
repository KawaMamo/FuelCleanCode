package org.example.repositories;

import org.example.repositories.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long>,
        PagingAndSortingRepository<CategoryEntity, Long>, JpaSpecificationExecutor<CategoryEntity> {
}
