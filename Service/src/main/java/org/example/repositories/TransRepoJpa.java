package org.example.repositories;

import org.example.repositories.entity.TransportationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TransRepoJpa extends JpaRepository<TransportationEntity, Long>,
        PagingAndSortingRepository<TransportationEntity, Long>,
        JpaSpecificationExecutor<TransportationEntity> {



}
