package org.example.contract.repository;

import org.example.model.Deleted;

import java.util.Optional;

public interface DeletedRepo {
    Deleted save(Deleted deleted);
    Optional<Deleted> findById(Long id);
    void delete(Deleted deleted);
}
