package org.example.controllers;

import org.example.contract.request.create.CreatePersonRequest;
import org.example.contract.request.update.UpdatePersonRequest;
import org.example.contract.response.PersonResponse;
import org.example.entities.PersonEntity;
import org.example.mappers.PersonMapper;
import org.example.model.Person;
import org.example.repositories.PersonRepository;
import org.example.specifications.CriteriaArrayToList;
import org.example.specifications.FilterSpecifications;
import org.example.specifications.SearchCriteria;
import org.example.specifications.SearchFilter;
import org.example.useCases.create.CreatePerson;
import org.example.useCases.update.UpdatePerson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/person")
public class PersonController {
    private final CreatePerson createPerson;
    private final PersonRepository personRepository;
    private final PersonMapper personMapper;
    private final PagedResourcesAssembler assembler;
    private final UpdatePerson updatePerson;

    public PersonController(CreatePerson createPerson,
                            PersonRepository personRepository,
                            PersonMapper personMapper,
                            PagedResourcesAssembler assembler, UpdatePerson updatePerson) {
        this.createPerson = createPerson;
        this.personRepository = personRepository;
        this.personMapper = personMapper;
        this.assembler = assembler;
        this.updatePerson = updatePerson;
    }
    @PostMapping
    public PersonResponse createPerson(@RequestBody CreatePersonRequest request){
        return createPerson.execute(request);
    }

    @GetMapping
    public PagedModel<PersonResponse> listPersons(SearchFilter filter, Pageable pageable){
        final List<SearchCriteria> criteriaList = CriteriaArrayToList.getCriteriaList(filter);
        final FilterSpecifications<PersonEntity> specifications = new FilterSpecifications<>(criteriaList);
        final Page<Person> page = personRepository.findAll(specifications, pageable).map(personMapper::entityToDomain);
        return assembler.toModel(page);
    }

    @PatchMapping
    public ResponseEntity<PersonResponse> updatePerson(@RequestBody UpdatePersonRequest request){
        return ResponseEntity.ok(updatePerson.execute(request));
    }
}
