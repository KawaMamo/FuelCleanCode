package org.example.contract.repository;

import org.example.model.ReturnedMaterial;

import java.util.Optional;

public interface ReturnedMaterialRepo {
    ReturnedMaterial save(ReturnedMaterial returnedMaterial);
    Optional<ReturnedMaterial> findById(Long id);
    void delete(ReturnedMaterial returnedMaterial);
}
