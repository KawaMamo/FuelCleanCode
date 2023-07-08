package org.example.repositories;

import org.example.entities.RegionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends JpaRepository<RegionEntity, Long>,
        PagingAndSortingRepository<RegionEntity, Long>, JpaSpecificationExecutor<RegionEntity> {
}
