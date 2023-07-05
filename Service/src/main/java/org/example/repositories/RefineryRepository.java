package org.example.repositories;

import org.example.repositories.entities.RefineryEntity;
import org.example.repositories.entities.TransportationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RefineryRepository extends JpaRepository<RefineryEntity, Long>, PagingAndSortingRepository<RefineryEntity, Long>,
        JpaSpecificationExecutor<RefineryEntity> {

}
