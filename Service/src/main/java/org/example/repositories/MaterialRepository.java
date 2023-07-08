package org.example.repositories;

import org.example.entities.MaterialEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialRepository extends JpaRepository<MaterialEntity, Long>,
        PagingAndSortingRepository<MaterialEntity, Long>, JpaSpecificationExecutor<MaterialEntity> {
}
