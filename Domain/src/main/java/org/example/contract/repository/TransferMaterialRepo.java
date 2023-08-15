package org.example.contract.repository;

import org.example.model.TransferMaterials;

import java.util.Optional;

public interface TransferMaterialRepo {
    TransferMaterials save(TransferMaterials transferMaterials);
    Optional<TransferMaterials> findById(Long id);
    void delete(TransferMaterials transferMaterials);
}
