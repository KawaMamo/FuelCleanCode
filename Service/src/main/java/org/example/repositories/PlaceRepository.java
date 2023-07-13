package org.example.repositories;

import org.example.entities.PlaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
@NoRepositoryBean
public interface PlaceRepository extends JpaRepository<PlaceEntity, Long>,
        PagingAndSortingRepository<PlaceEntity, Long>, JpaSpecificationExecutor<PlaceEntity> {
}
