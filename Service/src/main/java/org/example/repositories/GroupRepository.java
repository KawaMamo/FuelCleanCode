package org.example.repositories;

import org.example.entities.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<GroupEntity, Long>,
        PagingAndSortingRepository<GroupEntity, Long>, JpaSpecificationExecutor<GroupEntity> {
}
