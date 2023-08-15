package org.example.contract.repository;

import org.example.model.Vehicle;

import java.util.Optional;

public interface VehicleRepo {
    Vehicle save(Vehicle vehicle);
    Optional<Vehicle> findById(Long id);
    void delete(Vehicle vehicle);
}
