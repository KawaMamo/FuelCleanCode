package org.example.contract.repository;

import org.example.model.Forfeit;

import java.util.Optional;

public interface ForfeitRepo {
    Forfeit save(Forfeit forfeit);
    Optional<Forfeit> findById(Long id);
    void delete(Forfeit forfeit);
}
