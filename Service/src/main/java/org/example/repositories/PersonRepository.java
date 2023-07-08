package org.example.repositories;

import org.example.entities.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PersonRepository extends JpaRepository<PersonEntity, Long>,
        PagingAndSortingRepository<PersonEntity, Long>, JpaSpecificationExecutor<PersonEntity> {
}
