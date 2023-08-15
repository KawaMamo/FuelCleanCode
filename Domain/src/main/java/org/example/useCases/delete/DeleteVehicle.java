package org.example.useCases.delete;

import org.example.contract.repository.VehicleRepo;
import org.example.contract.response.VehicleResponse;
import org.example.mappers.VehicleDomainMapper;
import org.example.model.Vehicle;

import java.util.NoSuchElementException;

public class DeleteVehicle {
    private final VehicleRepo vehicleRepo;
    private final VehicleDomainMapper mapper;

    public DeleteVehicle(VehicleRepo vehicleRepo, VehicleDomainMapper mapper) {
        this.vehicleRepo = vehicleRepo;
        this.mapper = mapper;
    }

    public VehicleResponse execute(Long id){
        final Vehicle vehicle = vehicleRepo.findById(id).orElseThrow(NoSuchElementException::new);
        vehicleRepo.delete(vehicle);
        return mapper.domainToResponse(vehicle);
    }
}
