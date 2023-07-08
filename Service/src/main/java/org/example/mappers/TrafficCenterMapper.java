package org.example.mappers;

import org.example.model.TrafficCenter;
import org.example.entities.TrafficCenterEntity;
import org.mapstruct.Mapper;

@Mapper
public interface TrafficCenterMapper {
    TrafficCenterEntity domainToEntity(TrafficCenter trafficCenter);
    TrafficCenter entityToDomain(TrafficCenterEntity trafficCenter);
}
