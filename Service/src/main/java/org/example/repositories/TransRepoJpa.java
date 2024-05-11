package org.example.repositories;

import org.example.entities.TransLogEntity;
import org.example.entities.TransportationEntity;
import org.example.entities.TransportationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TransRepoJpa extends JpaRepository<TransportationEntity, Long>,
        PagingAndSortingRepository<TransportationEntity, Long>,
        JpaSpecificationExecutor<TransportationEntity> {

    @Query("SELECT t FROM TransportationEntity t JOIN t.vehicle v WHERE t.refinery.id = ?1 AND t.createdAt BETWEEN ?2 AND ?3 AND t.type = ?4")
    List<TransportationEntity> getTransportationEntityByRefinery(Long refineryId, LocalDateTime start, LocalDateTime end, TransportationType type);

}
