package org.example.repositories;

import org.example.entities.TransLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransLogRepository extends JpaRepository<TransLogEntity, Long>,
        PagingAndSortingRepository<TransLogEntity, Long>, JpaSpecificationExecutor<TransLogEntity> {
}
