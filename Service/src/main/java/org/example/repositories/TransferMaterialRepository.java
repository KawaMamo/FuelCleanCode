package org.example.repositories;

import org.example.entities.TransferMaterialsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransferMaterialRepository extends JpaRepository<TransferMaterialsEntity, Long>,
        PagingAndSortingRepository<TransferMaterialsEntity, Long>, JpaSpecificationExecutor<TransferMaterialsEntity> {

    @Query("SELECT SUM(t.amount*t.priceAmount), t.priceCurrency, t.material.name FROM TransferMaterialsEntity t JOIN t.source s WHERE s.id = ?1 GROUP BY t.priceCurrency, t.material")
    List<String[]> getTransferMaterialsFrom(Long gasStationId);

    @Query("SELECT SUM(t.amount*t.priceAmount), t.priceCurrency, t.material.name FROM TransferMaterialsEntity t JOIN t.destination s WHERE s.id = ?1 GROUP BY t.priceCurrency, t.material")
    List<String[]> getTransferMaterialsTo(Long gasStationId);
}
