package org.example.repositories;

import org.example.auxiliary.Production;
import org.example.entities.PartitionEntity;
import org.example.entities.TransportationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PartitionRepository extends JpaRepository<PartitionEntity, Long>,
        PagingAndSortingRepository<PartitionEntity, Long>, JpaSpecificationExecutor<PartitionEntity> {

    @Query("SELECT p FROM PartitionEntity p JOIN p.gasStation g JOIN g.region r JOIN p.transportationEntity t WHERE r.id = ?1 AND t.createdAt BETWEEN ?2 AND ?3 AND t.type = ?4")
    List<PartitionEntity> getPartitionEntities(Long id, LocalDateTime start, LocalDateTime end, TransportationType type);

    @Query("SELECT p FROM PartitionEntity p JOIN p.gasStation g JOIN g.region r JOIN p.transportationEntity t " +
            "WHERE p.transportationEntity.refinery.id = ?1 AND t.createdAt BETWEEN ?2 AND ?3 AND t.type = ?4")
    List<PartitionEntity> getPartitionEntitiesByRefinery(Long id, LocalDateTime start, LocalDateTime end, TransportationType type);

    @Query("SELECT SUM(p.amount) AS amountSum, p.material.name As materialName, r.name AS refineryName FROM PartitionEntity p " +
            "JOIN p.transportationEntity t JOIN t.refinery r " +
            "WHERE r.id = ?1 AND t.createdAt BETWEEN ?2 AND ?3 AND t.type = ?4 GROUP BY p.material ")
    List<String[]> getRefineryProduction(Long refineryId, LocalDateTime start, LocalDateTime end, TransportationType type);

    List<PartitionEntity> findByMaterial_IdAndTransportationEntity_TypeAndTransportationEntity_CreatedAtBetween(Long id, TransportationType type, LocalDateTime start, LocalDateTime end);
    @Query("SELECT p FROM PartitionEntity p JOIN p.gasStation g JOIN p.transportationEntity t " +
            "WHERE g.id = ?1 AND t.createdAt BETWEEN ?2 AND ?3 AND t.type = ?4")
    List<PartitionEntity> getPartitionsForClient(Long id, LocalDateTime start, LocalDateTime end, TransportationType type);

    @Query("SELECT p.priceCurrency, SUM(p.amount*p.priceAmount) FROM PartitionEntity p JOIN p.gasStation g " +
            "WHERE g.id = ?1 GROUP BY p.priceCurrency")
    List<String[]> getTotalMaterialsForClient(Long clientId);

}
