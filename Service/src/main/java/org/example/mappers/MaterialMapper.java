package org.example.mappers;

import org.example.model.Material;
import org.example.repositories.entities.MaterialEntity;
import org.mapstruct.Mapper;

@Mapper
public interface MaterialMapper {
    MaterialEntity domainToEntity(Material material);
    Material EntityToDomain(MaterialEntity material);
}
