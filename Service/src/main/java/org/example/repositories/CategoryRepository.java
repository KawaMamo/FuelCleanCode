package org.example.repositories;

import org.example.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long>,
        PagingAndSortingRepository<CategoryEntity, Long>, JpaSpecificationExecutor<CategoryEntity> {
}
