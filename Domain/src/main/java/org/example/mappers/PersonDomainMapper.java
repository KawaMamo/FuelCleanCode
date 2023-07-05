package org.example.mappers;

import org.example.contract.request.CreatePersonRequest;
import org.example.contract.response.PersonResponse;
import org.example.model.Person;
import org.mapstruct.Mapper;

@Mapper
public interface PersonDomainMapper {
    Person requestToDomain(CreatePersonRequest request);
    PersonResponse domainToResponse(Person person);
}
