package org.example.mappers;

import org.example.entities.DeletedEntity;
import org.example.model.Deleted;
import org.mapstruct.Mapper;

@Mapper
public interface DeletedMapper {
    DeletedEntity domainToEntity(Deleted deleted);
    Deleted entityToDomain(DeletedEntity entity);
}
