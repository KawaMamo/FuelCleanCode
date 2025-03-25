package org.example.repositories;

import org.example.entities.ForfeitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ForfeitRepository extends JpaRepository<ForfeitEntity, Long>,
        PagingAndSortingRepository<ForfeitEntity, Long>, JpaSpecificationExecutor<ForfeitEntity> {

    @Query("SELECT f FROM ForfeitEntity f JOIN f.vehicles v JOIN v.office o WHERE o.id =?1 AND f.createdAt BETWEEN ?2 AND ?3")
    List<ForfeitEntity> getForfeitEntityByOfficeId(Long id, LocalDateTime start, LocalDateTime end);
}
