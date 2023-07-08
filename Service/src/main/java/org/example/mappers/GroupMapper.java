package org.example.mappers;

import org.example.model.Group;
import org.example.entities.GroupEntity;
import org.mapstruct.Mapper;

@Mapper
public interface GroupMapper {
    GroupEntity domainToEntity(Group group);
    Group entityToDomain(GroupEntity group);
}
