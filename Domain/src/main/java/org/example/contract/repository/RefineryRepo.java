package org.example.contract.repository;

import org.example.model.Refinery;

import java.util.Optional;

public interface RefineryRepo {
    Refinery save(Refinery refinery);
    Optional<Refinery> findById(Long id);
}
