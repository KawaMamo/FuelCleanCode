package org.example.mappers;

import org.example.model.Person;
import org.example.entities.PersonEntity;
import org.mapstruct.Mapper;

@Mapper
public interface PersonMapper {
    PersonEntity domainToEntity(Person person);
    Person entityToDomain(PersonEntity person);
}
