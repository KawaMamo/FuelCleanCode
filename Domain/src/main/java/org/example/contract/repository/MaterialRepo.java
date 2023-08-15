package org.example.contract.repository;

import org.example.model.Material;

import java.util.Optional;

public interface MaterialRepo {
    Material save(Material material);
    Optional<Material> findById(Long id);
    void delete(Material material);
}
