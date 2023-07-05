package org.example.mappers;

import org.example.model.Office;
import org.example.model.Person;
import org.example.model.TrafficCenter;
import org.example.repositories.VehicleRepository;
import org.example.repositories.entities.*;
import org.mapstruct.Mapper;

@Mapper
public interface VehicleMapper {
    Vehicle domainToEntity(org.example.model.Vehicle vehicle);

    PersonEntity domainToEntity(Person person);

    BuyOperation domainToEntity(org.example.model.BuyOperation buyOperation);

    org.example.model.Vehicle EntityToDomain(Vehicle save);

    OfficeEntity domainToEntity(Office office);

    TrafficCenterEntity domainToEntity(TrafficCenter trafficCenter);
}
