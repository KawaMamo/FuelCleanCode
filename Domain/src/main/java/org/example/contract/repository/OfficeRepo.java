package org.example.contract.repository;

import org.example.model.Office;

import java.util.Optional;

public interface OfficeRepo {
    Office save(Office office);
    Optional<Office> findById(Long id);
}
