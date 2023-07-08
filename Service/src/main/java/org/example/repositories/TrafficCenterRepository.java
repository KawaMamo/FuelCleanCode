package org.example.repositories;

import org.example.entities.TrafficCenterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrafficCenterRepository extends JpaRepository<TrafficCenterEntity, Long>,
        PagingAndSortingRepository<TrafficCenterEntity, Long>, JpaSpecificationExecutor<TrafficCenterEntity> {
}
