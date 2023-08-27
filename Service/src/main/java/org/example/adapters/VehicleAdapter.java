package org.example.adapters;

import org.example.contract.repository.VehicleRepo;
import org.example.mappers.VehicleMapper;
import org.example.model.Vehicle;
import org.example.repositories.VehicleRepository;
import org.example.entities.VehicleEntity;

import java.util.Optional;

public class VehicleAdapter implements VehicleRepo {
    private final VehicleRepository vehicleRepository;
    private final VehicleMapper mapper;

    public VehicleAdapter(VehicleRepository vehicleRepository, VehicleMapper mapper) {
        this.vehicleRepository = vehicleRepository;
        this.mapper = mapper;
    }

    @Override
    public Vehicle save(Vehicle vehicle) {
        final VehicleEntity entity = mapper.domainToEntity(vehicle);
        final VehicleEntity save = vehicleRepository.save(entity);
        return mapper.entityToDomain(save);
    }

    @Override
    public Optional<Vehicle> findById(Long id) {
        final Optional<VehicleEntity> byId = vehicleRepository.findById(id);
        final VehicleEntity vehicleEntity = byId.orElse(null);
        final Vehicle vehicle = mapper.entityToDomain(vehicleEntity);
        return Optional.ofNullable(vehicle);
    }

    @Override
    public void delete(Vehicle vehicle) {
        vehicleRepository.delete(mapper.domainToEntity(vehicle));
    }
}
