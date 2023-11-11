package org.example.mappers;

import org.example.entities.*;
import org.example.model.Office;
import org.example.model.Person;
import org.example.model.TrafficCenter;
import org.mapstruct.Mapper;

@Mapper(uses = TransLogMapper.class)
public interface VehicleMapper {


    VehicleEntity domainToEntity(org.example.model.Vehicle vehicle);

    PersonEntity domainToEntity(Person person);

    BuyOperation domainToEntity(org.example.model.BuyOperation buyOperation);

    org.example.model.Vehicle entityToDomain(VehicleEntity save);

    OfficeEntity domainToEntity(Office office);

    TrafficCenterEntity domainToEntity(TrafficCenter trafficCenter);
}
