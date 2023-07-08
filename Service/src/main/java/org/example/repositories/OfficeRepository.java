package org.example.repositories;

import org.example.entities.OfficeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface OfficeRepository extends JpaRepository<OfficeEntity, Long> , PagingAndSortingRepository<OfficeEntity, Long>,
        JpaSpecificationExecutor<OfficeEntity> {
}
