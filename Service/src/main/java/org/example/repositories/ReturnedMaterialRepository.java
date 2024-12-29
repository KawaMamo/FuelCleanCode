package org.example.repositories;

import org.example.entities.ReturnedMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ReturnedMaterialRepository extends JpaRepository<ReturnedMaterial, Long>,
        PagingAndSortingRepository<ReturnedMaterial, Long>, JpaSpecificationExecutor<ReturnedMaterial> {
    @Query("SELECT SUM(r.amount*r.priceAmount), r.priceCurrency, r.material.name FROM ReturnedMaterial r JOIN r.gasStation g WHERE g.id = ?1 GROUP BY r.priceCurrency, r.material")
    List<String[]> getReturnedMaterials(Long gasStationId);
}
