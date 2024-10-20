package org.example.contract.repository;

import org.example.model.Buyer;

import java.util.Optional;

public interface BuyerRepo {
    Buyer save(Buyer buyer);
    Optional<Buyer> findById(Long id);
    void delete(Buyer buyer);
}
