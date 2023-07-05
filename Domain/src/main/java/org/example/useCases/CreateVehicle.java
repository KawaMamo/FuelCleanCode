package org.example.useCases;

import org.example.contract.repository.VehicleRepo;
import org.example.contract.request.CreateVehicleRequest;
import org.example.contract.response.VehicleResponse;
import org.example.mappers.VehicleDomainMapper;
import org.example.model.Vehicle;
import org.example.validators.CreateVehicleValidator;

import java.time.LocalDateTime;

public class CreateVehicle {

    private final CreateVehicleValidator validator;
    private final VehicleDomainMapper mapper;
    private final VehicleRepo vehicleRepo;

    public CreateVehicle(CreateVehicleValidator validator,
                         VehicleDomainMapper mapper,
                         VehicleRepo vehicleRepo) {
        this.validator = validator;
        this.mapper = mapper;
        this.vehicleRepo = vehicleRepo;
    }

    public VehicleResponse execute(CreateVehicleRequest request){

        validator.validate(request);
        final Vehicle vehicle = mapper.requestToDomain(request);
        vehicle.setCreatedAt(LocalDateTime.now());
        final Vehicle save = vehicleRepo.save(vehicle);
        return mapper.domainToResponse(save);
    }
}
