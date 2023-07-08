package org.example.controllers;

import org.example.contract.request.CreatePersonRequest;
import org.example.contract.response.PersonResponse;
import org.example.useCases.CreatePerson;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/person")
public class PersonController {
    private final CreatePerson createPerson;

    public PersonController(CreatePerson createPerson) {
        this.createPerson = createPerson;
    }
    @PostMapping
    public PersonResponse createPerson(@RequestBody CreatePersonRequest request){
        return createPerson.execute(request);
    }
}
