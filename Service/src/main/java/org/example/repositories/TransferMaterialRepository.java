package org.example.repositories;

import org.example.entities.PartitionEntity;
import org.example.entities.TransferMaterialsEntity;
import org.example.entities.TransportationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransferMaterialRepository extends JpaRepository<TransferMaterialsEntity, Long>,
        PagingAndSortingRepository<TransferMaterialsEntity, Long>, JpaSpecificationExecutor<TransferMaterialsEntity> {

    @Query("SELECT t.priceCurrency, SUM(t.amount*t.priceAmount) FROM TransferMaterialsEntity t JOIN t.source s WHERE s.id = ?1 GROUP BY t.priceCurrency")
    List<String[]> getTransferMaterialsFrom(Long gasStationId);

    @Query("SELECT t.priceCurrency, SUM(t.amount*t.priceAmount) FROM TransferMaterialsEntity t JOIN t.destination s WHERE s.id = ?1 GROUP BY t.priceCurrency")
    List<String[]> getTransferMaterialsTo(Long gasStationId);

    @Query("SELECT t FROM TransferMaterialsEntity t JOIN t.destination s JOIN t.source o WHERE ((s.id = ?1 OR o.id = ?1) AND t.createdAt BETWEEN ?2 AND ?3 AND t.type = ?4)")
    List<TransferMaterialsEntity> getTransferMaterials(Long id, LocalDateTime startDate, LocalDateTime endDate, TransportationType type);
}
