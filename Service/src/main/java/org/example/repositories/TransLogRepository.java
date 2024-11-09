package org.example.repositories;

import org.example.auxiliary.TransLogAggregated;
import org.example.entities.TransLogEntity;
import org.example.entities.TransportationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransLogRepository extends JpaRepository<TransLogEntity, Long>,
        PagingAndSortingRepository<TransLogEntity, Long>, JpaSpecificationExecutor<TransLogEntity> {

    @Query("SELECT t FROM TransLogEntity t JOIN t.vehicle v JOIN t.transportation r JOIN t.transLine n WHERE v.id = ?1 AND t.createdAt BETWEEN ?2 AND ?3 AND r.type = ?4")
    List<TransLogEntity> getTransLogEntities(Long vehicleId, LocalDateTime start, LocalDateTime end, TransportationType type);

    @Query("SELECT t FROM TransLogEntity t JOIN t.vehicle v JOIN t.transportation r JOIN t.transLine n WHERE r.refinery.id = ?1 AND t.createdAt BETWEEN ?2 AND ?3 AND r.type = ?4")
    List<TransLogEntity> getTransLogEntitiesByRefinery(Long refineryId, LocalDateTime start, LocalDateTime end, TransportationType type);

    @Query("SELECT t, SUM(t.feesAmount) AS sum FROM TransLogEntity t " +
            "JOIN t.vehicle v " +
            "JOIN t.transportation r " +
            "WHERE v.office.id = ?1 AND t.createdAt BETWEEN ?2 AND ?3 AND r.type = ?4 " +
            "GROUP BY t.vehicle, t.feesCurrency")
    List<TransLogEntity> getTransLog(Long OfficeId, LocalDateTime start, LocalDateTime end, TransportationType type);

    @Query("SELECT t, SUM(t.feesAmount) AS sum, COUNT(t.id) AS count, SUM(f.priceAmount) AS forfeitSum, f.priceCurrency AS forfeitCurrency " +
            " FROM TransLogEntity t " +
            "JOIN t.vehicle v " +
            "JOIN t.transportation r LEFT JOIN r.partitionEntities p JOIN p.forfeit f " +
            "WHERE v.office.id = ?1 AND t.createdAt BETWEEN ?2 AND ?3 AND r.type = ?4 " +
            "GROUP BY t.vehicle, t.feesCurrency, f.priceCurrency")
    List<Object[]> getTransObj(Long OfficeId, LocalDateTime start, LocalDateTime end, TransportationType type);
}
