package org.example.repositories.adapters;

import org.example.contract.repository.VehicleRepo;
import org.example.mappers.VehicleMapper;
import org.example.model.Vehicle;
import org.example.repositories.VehicleRepository;

public class VehicleAdapter implements VehicleRepo {
    private final VehicleRepository vehicleRepository;
    private final VehicleMapper mapper;

    public VehicleAdapter(VehicleRepository vehicleRepository, VehicleMapper mapper) {
        this.vehicleRepository = vehicleRepository;
        this.mapper = mapper;
    }

    @Override
    public Vehicle save(Vehicle vehicle) {
        final org.example.repositories.entities.Vehicle entity = mapper.domainToEntity(vehicle);
        final org.example.repositories.entities.Vehicle save = vehicleRepository.save(entity);
        return mapper.EntityToDomain(save);
    }
}
