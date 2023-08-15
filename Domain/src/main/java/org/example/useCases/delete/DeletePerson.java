package org.example.useCases.delete;

import org.example.contract.repository.PersonRepo;
import org.example.contract.response.PersonResponse;
import org.example.mappers.PersonDomainMapper;
import org.example.model.Person;

import java.util.NoSuchElementException;

public class DeletePerson {
    private final PersonRepo personRepo;
    private final PersonDomainMapper mapper;
    public DeletePerson(PersonRepo personRepo, PersonDomainMapper mapper) {
        this.personRepo = personRepo;
        this.mapper = mapper;
    }
    public PersonResponse execute(Long id){
        final Person person = personRepo.findById(id).orElseThrow(NoSuchElementException::new);
        personRepo.delete(person);
        return mapper.domainToResponse(person);
    }
}
