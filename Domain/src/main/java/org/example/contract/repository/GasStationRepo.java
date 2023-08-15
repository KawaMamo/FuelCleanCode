package org.example.contract.repository;

import org.example.model.GasStation;

import java.util.Optional;

public interface GasStationRepo{
    GasStation save(GasStation gasStation);
    Optional<GasStation> findById(Long id);
    void delete(GasStation gasStation);
}
