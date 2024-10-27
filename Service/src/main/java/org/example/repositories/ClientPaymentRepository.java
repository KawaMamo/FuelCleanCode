package org.example.repositories;

import org.example.entities.ClientPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientPaymentRepository extends JpaRepository<ClientPayment, Long>,
        PagingAndSortingRepository<ClientPayment, Long>, JpaSpecificationExecutor<ClientPayment> {
}
