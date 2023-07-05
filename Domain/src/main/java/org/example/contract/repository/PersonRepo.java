package org.example.contract.repository;

import org.example.model.Person;

public interface PersonRepo {
    Person save(Person person);
}
