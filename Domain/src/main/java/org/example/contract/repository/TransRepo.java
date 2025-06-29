package org.example.contract.repository;

import org.example.model.Transportation;

import java.time.LocalDateTime;
import java.util.Optional;

public interface TransRepo {
    Transportation save(Transportation transportation);
    Optional<Transportation> findByIdAndDeletedAt(Long id, LocalDateTime deletedAt);
    Optional<Transportation> findById(Long id);
    void delete(Transportation transportation);
}
