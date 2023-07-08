package org.example.mappers;

import org.example.model.Refinery;
import org.example.entities.RefineryEntity;
import org.mapstruct.Mapper;

@Mapper
public interface RefineryMapper {
    RefineryEntity toEntity(Refinery refinery);
    Refinery toDomain(RefineryEntity refineryEntity);
}
