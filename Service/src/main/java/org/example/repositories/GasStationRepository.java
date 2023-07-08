package org.example.repositories;

import org.example.entities.GasStationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GasStationRepository extends JpaRepository<GasStationEntity, Long>,
        PagingAndSortingRepository<GasStationEntity, Long>, JpaSpecificationExecutor<GasStationEntity> {
}
