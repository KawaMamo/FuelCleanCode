package org.example.repositories;

import org.example.entities.ClientPayment;
import org.example.entities.ReturnedMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
@Repository
public interface ReturnedMaterialRepository extends JpaRepository<ReturnedMaterial, Long>,
        PagingAndSortingRepository<ReturnedMaterial, Long>, JpaSpecificationExecutor<ReturnedMaterial> {
    @Query("SELECT r.priceCurrency, SUM(r.amount*r.priceAmount) FROM ReturnedMaterial r JOIN r.gasStation g WHERE g.id = ?1 GROUP BY r.priceCurrency")
    List<String[]> getReturnedMaterials(Long gasStationId);

    @Query("SELECT r FROM ReturnedMaterial r JOIN r.gasStation g WHERE g.id = ?1 and r.createdAt BETWEEN ?2 AND ?3 ORDER BY r.createdAt")
    List<ReturnedMaterial> getReturnedMaterialsList(Long id, LocalDateTime start, LocalDateTime end);
}
