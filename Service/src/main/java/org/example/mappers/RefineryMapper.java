package org.example.mappers;

import org.example.model.Refinery;
import org.example.entities.RefineryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface RefineryMapper {
    @Mapping(source = "region", target = "regionEntity")
    RefineryEntity toEntity(Refinery refinery);
    @Mapping(target = "region", source = "regionEntity")
    Refinery toDomain(RefineryEntity refineryEntity);
}
