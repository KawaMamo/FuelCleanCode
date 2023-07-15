package org.example.repositories;

import org.example.entities.TransLineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransLineRepository extends JpaRepository<TransLineEntity, Long>,
        PagingAndSortingRepository<TransLineEntity, Long>, JpaSpecificationExecutor<TransLineEntity> {
}
