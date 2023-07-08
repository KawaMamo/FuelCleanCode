package org.example.contract.repository;

import org.example.model.Person;

import java.util.Optional;

public interface PersonRepo {
    Person save(Person person);
    Optional<Person> findById(Long id);
}
