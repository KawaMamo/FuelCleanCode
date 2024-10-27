package org.example.repositories;

import org.example.entities.PaidToParent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaidToParentRepository extends JpaRepository<PaidToParent, Long>,
        PagingAndSortingRepository<PaidToParent, Long>, JpaSpecificationExecutor<PaidToParent> {
}
