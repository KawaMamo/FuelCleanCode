package org.example.repositories;

import org.example.entities.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long>, PagingAndSortingRepository<Seller, Long>,
        JpaSpecificationExecutor<Seller> {
}
