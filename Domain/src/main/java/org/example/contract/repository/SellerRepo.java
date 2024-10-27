package org.example.contract.repository;

import org.example.model.Seller;

import java.util.Optional;

public interface SellerRepo {
    Seller save(Seller seller);
    Optional<Seller> findById(Long id);
    void delete(Seller seller);
}
