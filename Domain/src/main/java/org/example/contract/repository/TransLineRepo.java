package org.example.contract.repository;

import org.example.model.TransLine;

import java.util.Optional;

public interface TransLineRepo {
    TransLine save(TransLine transLine);
    Optional<TransLine> findById(Long id);
}
