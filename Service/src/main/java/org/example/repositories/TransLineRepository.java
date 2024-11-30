package org.example.repositories;

import org.example.entities.TransLineEntity;
import org.example.model.GasStation;
import org.example.model.TransLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransLineRepository extends JpaRepository<TransLineEntity, Long>,
        PagingAndSortingRepository<TransLineEntity, Long>, JpaSpecificationExecutor<TransLineEntity> {

    Optional<TransLineEntity> findBySourceAndDestination(GasStation source, GasStation destination);
}
