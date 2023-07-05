package org.example.useCases;

import org.example.contract.repository.PersonRepo;
import org.example.contract.request.CreatePersonRequest;
import org.example.contract.response.PersonResponse;
import org.example.mappers.PersonDomainMapper;
import org.example.model.Person;
import org.example.validators.CreatePersonValidator;

import java.time.LocalDateTime;

public class CreatePerson {

    private final PersonRepo personRepo;
    private final PersonDomainMapper mapper;
    private final CreatePersonValidator validator;

    public CreatePerson(PersonRepo personRepo, PersonDomainMapper mapper, CreatePersonValidator validator) {
        this.personRepo = personRepo;
        this.mapper = mapper;
        this.validator = validator;
    }

    public PersonResponse execute(CreatePersonRequest request){
        validator.validate(request);
        final Person person = mapper.requestToDomain(request);
        final Person save = personRepo.save(person);
        save.setCreatedAt(LocalDateTime.now());
        return mapper.domainToResponse(save);
    }
}
