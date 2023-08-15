package org.example.contract.repository;

import org.example.model.Transportation;

import java.util.Optional;

public interface TransRepo {
    Transportation save(Transportation transportation);
    Optional<Transportation> findById(Long id);
    void delete(Transportation transportation);
}
