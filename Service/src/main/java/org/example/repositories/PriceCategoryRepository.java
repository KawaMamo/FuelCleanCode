package org.example.repositories;

import org.example.entities.PriceCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PriceCategoryRepository extends JpaRepository<PriceCategoryEntity, Long>,
        PagingAndSortingRepository<PriceCategoryEntity, Long>, JpaSpecificationExecutor<PriceCategoryEntity> {

}
