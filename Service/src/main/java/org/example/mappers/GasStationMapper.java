package org.example.mappers;

import org.example.model.GasStation;
import org.example.entities.GasStationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface GasStationMapper {
    @Mapping(source = "priceCategory.id", target = "priceCategoryId")
    @Mapping(source = "region.id", target = "regionId")
    @Mapping(source = "owner.id", target = "ownerId")
    @Mapping(source = "group.id", target = "groupId")
    GasStationEntity domainToEntity(GasStation gasStation);
    @Mapping(target = "priceCategory.id", source = "priceCategoryId")
    @Mapping(target = "region.id", source = "regionId")
    @Mapping(target = "owner.id", source = "ownerId")
    @Mapping(target = "group.id", source = "groupId")
    GasStation entityToDomain(GasStationEntity gasStation);
}
