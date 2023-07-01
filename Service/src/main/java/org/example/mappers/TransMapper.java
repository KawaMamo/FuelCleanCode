package org.example.mappers;

import org.example.model.*;
import org.example.repositories.entity.*;
import org.example.repositories.entity.Vehicle;
import org.mapstruct.Mapper;

@Mapper
public interface TransMapper {

    TransportationEntity transportationEntity(Transportation transportation);

    Transportation toTransportation(TransportationEntity transportationEntity);

    Vehicle toVehicle(org.example.model.Vehicle vehicle);

    RefineryEntity toRefinery(org.example.model.Refinery refinery);

    TrafficCenterEntity toEntity(TrafficCenter trafficCenter);

    OfficeEntity toEntity(Office office);

    PersonEntity toEntity(Person person);
}
