package org.example.mappers;

import javax.annotation.processing.Generated;
import org.example.contract.request.create.CreatePersonRequest;
import org.example.contract.request.update.UpdatePersonRequest;
import org.example.contract.response.PersonResponse;
import org.example.model.Person;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-29T20:43:01+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
public class PersonDomainMapperImpl implements PersonDomainMapper {

    @Override
    public Person requestToDomain(CreatePersonRequest request) {
        if ( request == null ) {
            return null;
        }

        Person person = new Person();

        person.setName( request.getName() );
        person.setFather( request.getFather() );
        person.setMother( request.getMother() );
        person.setNationalId( request.getNationalId() );
        person.setBirthPlace( request.getBirthPlace() );
        person.setBirthDate( request.getBirthDate() );

        return person;
    }

    @Override
    public PersonResponse domainToResponse(Person person) {
        if ( person == null ) {
            return null;
        }

        PersonResponse personResponse = new PersonResponse();

        personResponse.setId( person.getId() );
        personResponse.setName( person.getName() );
        personResponse.setFather( person.getFather() );
        personResponse.setMother( person.getMother() );
        personResponse.setNationalId( person.getNationalId() );
        personResponse.setBirthPlace( person.getBirthPlace() );
        personResponse.setBirthDate( person.getBirthDate() );
        personResponse.setCreatedAt( person.getCreatedAt() );
        personResponse.setUpdatedAt( person.getUpdatedAt() );

        return personResponse;
    }

    @Override
    public Person requestToDomain(UpdatePersonRequest request) {
        if ( request == null ) {
            return null;
        }

        Person person = new Person();

        person.setId( request.getId() );
        person.setName( request.getName() );
        person.setFather( request.getFather() );
        person.setMother( request.getMother() );
        person.setNationalId( request.getNationalId() );
        person.setBirthPlace( request.getBirthPlace() );
        person.setBirthDate( request.getBirthDate() );

        return person;
    }
}
