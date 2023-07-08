package org.example.mappers;

import org.example.model.Region;
import org.example.entities.RegionEntity;
import org.mapstruct.Mapper;

@Mapper
public interface RegionMapper {
    RegionEntity domainToEntity(Region region);
    Region entityToDomain(RegionEntity region);
}
