package org.example.repositories;

import org.example.entities.SellerPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerPaymentRepository extends JpaRepository<SellerPayment, Long>,
        PagingAndSortingRepository<SellerPayment, Long>, JpaSpecificationExecutor<SellerPayment> {
}
