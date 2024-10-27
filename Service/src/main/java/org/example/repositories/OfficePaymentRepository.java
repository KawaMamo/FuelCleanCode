package org.example.repositories;

import org.example.entities.OfficePayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficePaymentRepository extends JpaRepository<OfficePayment, Long>,
        PagingAndSortingRepository<OfficePayment, Long>, JpaSpecificationExecutor<OfficePayment> {
}
