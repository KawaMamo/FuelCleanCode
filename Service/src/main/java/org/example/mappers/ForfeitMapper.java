package org.example.mappers;

import org.example.entities.ForfeitEntity;
import org.example.model.Forfeit;
import org.mapstruct.Mapper;

@Mapper
public interface ForfeitMapper {
    Forfeit entityToDomain(ForfeitEntity forfeit);
    ForfeitEntity domainToEntity(Forfeit forfeit);
}
