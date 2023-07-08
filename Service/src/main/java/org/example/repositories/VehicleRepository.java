package org.example.repositories;

import org.example.entities.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface VehicleRepository extends JpaRepository<VehicleEntity, Long>, PagingAndSortingRepository<VehicleEntity, Long>,
        JpaSpecificationExecutor<VehicleEntity> {

}
