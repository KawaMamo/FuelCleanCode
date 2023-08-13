package org.example.mappers;

import org.example.contract.request.create.CreateVehicleRequest;
import org.example.contract.request.update.UpdateVehicleRequest;
import org.example.contract.response.VehicleResponse;
import org.example.model.Vehicle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface VehicleDomainMapper {
    @Mapping(source = "trafficCenter_id", target = "trafficCenter.id")
    @Mapping(source = "office_id", target = "office.id")
    @Mapping(source = "driver_id", target = "driver.id")
    Vehicle requestToDomain(CreateVehicleRequest request);
    VehicleResponse domainToResponse(Vehicle vehicle);
    @Mapping(source = "trafficCenter_id", target = "trafficCenter.id")
    @Mapping(source = "office_id", target = "office.id")
    @Mapping(source = "driver_id", target = "driver.id")
    Vehicle requestToDomain(UpdateVehicleRequest request);
}
