package org.example.repositories;

import org.example.entities.TransferMaterialsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferMaterialRepository extends JpaRepository<TransferMaterialsEntity, Long>,
        PagingAndSortingRepository<TransferMaterialsEntity, Long>, JpaSpecificationExecutor<TransferMaterialsEntity> {
}
