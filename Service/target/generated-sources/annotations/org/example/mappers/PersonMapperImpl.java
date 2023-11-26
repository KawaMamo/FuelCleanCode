package org.example.mappers;

import javax.annotation.processing.Generated;
import org.example.entities.PersonEntity;
import org.example.model.Person;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-25T14:56:29+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 20 (Oracle Corporation)"
)
public class PersonMapperImpl implements PersonMapper {

    @Override
    public PersonEntity domainToEntity(Person person) {
        if ( person == null ) {
            return null;
        }

        PersonEntity personEntity = new PersonEntity();

        personEntity.setId( person.getId() );
        personEntity.setName( person.getName() );
        personEntity.setFather( person.getFather() );
        personEntity.setMother( person.getMother() );
        personEntity.setNationalId( person.getNationalId() );
        personEntity.setBirthPlace( person.getBirthPlace() );
        personEntity.setBirthDate( person.getBirthDate() );
        personEntity.setCreatedAt( person.getCreatedAt() );
        personEntity.setUpdatedAt( person.getUpdatedAt() );

        return personEntity;
    }

    @Override
    public Person entityToDomain(PersonEntity person) {
        if ( person == null ) {
            return null;
        }

        Person person1 = new Person();

        person1.setId( person.getId() );
        person1.setName( person.getName() );
        person1.setFather( person.getFather() );
        person1.setMother( person.getMother() );
        person1.setNationalId( person.getNationalId() );
        person1.setBirthPlace( person.getBirthPlace() );
        person1.setBirthDate( person.getBirthDate() );
        person1.setCreatedAt( person.getCreatedAt() );
        person1.setUpdatedAt( person.getUpdatedAt() );

        return person1;
    }
}
