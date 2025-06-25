package org.example.repositories;

import org.example.entities.DeletedEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeletedRepository extends JpaRepository<DeletedEntity, Long>, PagingAndSortingRepository<DeletedEntity, Long> {
}
