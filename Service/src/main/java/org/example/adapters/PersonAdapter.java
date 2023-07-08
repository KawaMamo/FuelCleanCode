package org.example.adapters;

import org.example.contract.repository.PersonRepo;
import org.example.mappers.PersonMapper;
import org.example.model.Person;
import org.example.repositories.PersonRepository;
import org.example.entities.PersonEntity;

import java.time.LocalDateTime;
import java.util.Optional;

public class PersonAdapter implements PersonRepo {
    private final PersonRepository repository;
    private final PersonMapper mapper;

    public PersonAdapter(PersonRepository repository, PersonMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Person save(Person person) {
        final PersonEntity personEntity = mapper.domainToEntity(person);
        personEntity.setCreatedAt(LocalDateTime.now());
        final PersonEntity save = repository.save(personEntity);
        return mapper.entityToDomain(save);
    }

    @Override
    public Optional<Person> findById(Long id) {
        final Optional<PersonEntity> byId = repository.findById(id);
        final PersonEntity personEntity = byId.orElse(null);
        final Person person = mapper.entityToDomain(personEntity);
        return Optional.ofNullable(person);
    }
}
