package org.example.mappers;

import org.example.model.GasStation;
import org.example.entities.GasStationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface GasStationMapper {

    GasStationEntity domainToEntity(GasStation gasStation);

    GasStation entityToDomain(GasStationEntity gasStation);
}
