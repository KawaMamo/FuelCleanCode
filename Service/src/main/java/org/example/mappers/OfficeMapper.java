package org.example.mappers;

import org.example.model.Office;
import org.example.entities.OfficeEntity;
import org.mapstruct.Mapper;

@Mapper
public interface OfficeMapper {
    OfficeEntity domainToEntity(Office office);
    Office entityToDomain(OfficeEntity officeEntity);
}
