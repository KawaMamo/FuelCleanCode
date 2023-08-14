package org.example.useCases.update;

import org.example.contract.repository.VehicleRepo;
import org.example.contract.request.update.UpdateVehicleRequest;
import org.example.contract.response.VehicleResponse;
import org.example.mappers.VehicleDomainMapper;
import org.example.model.Vehicle;
import org.example.validators.update.UpdateVehicleValidator;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

public class UpdateVehicle {
    private final UpdateVehicleValidator updateVehicleValidator;
    private final VehicleRepo vehicleRepo;
    private final VehicleDomainMapper mapper;

    public UpdateVehicle(UpdateVehicleValidator updateVehicleValidator, VehicleRepo vehicleRepo, VehicleDomainMapper mapper) {
        this.updateVehicleValidator = updateVehicleValidator;
        this.vehicleRepo = vehicleRepo;
        this.mapper = mapper;
    }

    public VehicleResponse execute(UpdateVehicleRequest request) {
        final Vehicle original = vehicleRepo.findById(request.getId()).orElseThrow(NoSuchElementException::new);
        updateVehicleValidator.validate(request);
        Vehicle vehicle = mapper.requestToDomain(request);
        vehicle.setCreatedAt(original.getCreatedAt());
        vehicle.setUpdatedAt(LocalDateTime.now());
        vehicleRepo.save(vehicle);
        return mapper.domainToResponse(vehicle);
    }
}
