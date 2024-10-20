package org.example.repositories;

import org.example.entities.ReturnedMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ReturnedMaterialRepository extends JpaRepository<ReturnedMaterial, Long>,
        PagingAndSortingRepository<ReturnedMaterial, Long>, JpaSpecificationExecutor<ReturnedMaterial> {
}
