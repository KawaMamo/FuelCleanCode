package org.example.useCases.update;

import org.example.contract.repository.PersonRepo;
import org.example.contract.request.update.UpdatePersonRequest;
import org.example.contract.response.PersonResponse;
import org.example.mappers.PersonDomainMapper;
import org.example.model.Person;
import org.example.validators.update.UpdatePersonValidator;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

public class UpdatePerson {

    private final UpdatePersonValidator validator;
    private final PersonDomainMapper mapper;
    private final PersonRepo personRepo;

    public UpdatePerson(UpdatePersonValidator validator,
                        PersonDomainMapper mapper,
                        PersonRepo personRepo) {
        this.validator = validator;
        this.mapper = mapper;
        this.personRepo = personRepo;
    }

    public PersonResponse execute(UpdatePersonRequest request){
        final Person original = personRepo.findById(request.getId()).orElseThrow(NoSuchElementException::new);
        validator.validate(request);
        final Person person = mapper.requestToDomain(request);
        person.setCreatedAt(original.getCreatedAt());
        person.setUpdatedAt(LocalDateTime.now());
        final Person save = personRepo.save(person);
        return mapper.domainToResponse(save);
    }
}
