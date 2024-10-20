package org.example.repositories;

import org.example.entities.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BuyerRepository extends JpaRepository<Buyer, Long>,
        PagingAndSortingRepository<Buyer, Long>,
        JpaSpecificationExecutor<Buyer> {

}
