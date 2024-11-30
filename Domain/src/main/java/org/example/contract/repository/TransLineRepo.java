package org.example.contract.repository;

import org.example.model.GasStation;
import org.example.model.TransLine;

import java.util.Optional;

public interface TransLineRepo {
    TransLine save(TransLine transLine);
    Optional<TransLine> findById(Long id);
    void delete(TransLine transLine);

    Optional<TransLine> findBySourceAndDestination(GasStation source,GasStation destination);
}
