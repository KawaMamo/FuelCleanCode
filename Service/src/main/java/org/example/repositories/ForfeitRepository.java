package org.example.repositories;

import org.example.entities.ForfeitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForfeitRepository extends JpaRepository<ForfeitEntity, Long>,
        PagingAndSortingRepository<ForfeitEntity, Long>, JpaSpecificationExecutor<ForfeitEntity> {
}
