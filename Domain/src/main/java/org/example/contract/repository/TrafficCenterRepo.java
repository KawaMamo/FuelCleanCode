package org.example.contract.repository;

import org.example.model.TrafficCenter;

import java.util.Optional;

public interface TrafficCenterRepo {
    TrafficCenter save(TrafficCenter trafficCenter);
    Optional<TrafficCenter> findById(Long id);
    void delete(TrafficCenter trafficCenter);
}
