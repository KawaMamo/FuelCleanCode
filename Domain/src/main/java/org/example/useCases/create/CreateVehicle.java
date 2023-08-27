package org.example.useCases.create;

import org.example.contract.repository.OfficeRepo;
import org.example.contract.repository.PersonRepo;
import org.example.contract.repository.TrafficCenterRepo;
import org.example.contract.repository.VehicleRepo;
import org.example.contract.request.create.CreateVehicleRequest;
import org.example.contract.response.VehicleResponse;
import org.example.mappers.VehicleDomainMapper;
import org.example.model.Vehicle;
import org.example.validators.create.CreateVehicleValidator;

import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class CreateVehicle {

    private final CreateVehicleValidator validator;
    private final VehicleDomainMapper mapper;
    private final VehicleRepo vehicleRepo;
    private final TrafficCenterRepo trafficCenterRepo;
    private final OfficeRepo officeRepo;
    private final PersonRepo personRepo;

    public CreateVehicle(CreateVehicleValidator validator,
                         VehicleDomainMapper mapper,
                         VehicleRepo vehicleRepo, TrafficCenterRepo trafficCenterRepo, OfficeRepo officeRepo, PersonRepo personRepo) {
        this.validator = validator;
        this.mapper = mapper;
        this.vehicleRepo = vehicleRepo;
        this.trafficCenterRepo = trafficCenterRepo;
        this.officeRepo = officeRepo;
        this.personRepo = personRepo;
    }

    public VehicleResponse execute(CreateVehicleRequest request){

        validator.validate(request);
        final Vehicle vehicle = mapper.requestToDomain(request);
        vehicle.setCreatedAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        final Vehicle save = vehicleRepo.save(vehicle);
        personRepo.findById(save.getDriver().getId()).ifPresent(save::setDriver);
        trafficCenterRepo.findById(save.getTrafficCenter().getId()).ifPresent(save::setTrafficCenter);
        officeRepo.findById(save.getOffice().getId()).ifPresent(save::setOffice);
        return mapper.domainToResponse(save);
    }
}
