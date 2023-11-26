package org.example.repositories;

import org.example.entities.PartitionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartitionRepository extends JpaRepository<PartitionEntity, Long>,
        PagingAndSortingRepository<PartitionEntity, Long>, JpaSpecificationExecutor<PartitionEntity> {

    @Query("SELECT p FROM PartitionEntity p JOIN p.gasStation g JOIN g.region r WHERE r.id = ?1")
    List<PartitionEntity> getPartitionEntities(Long id);

}
