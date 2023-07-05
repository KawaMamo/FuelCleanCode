package org.example.useCases;

import org.example.contract.repository.TransRepo;
import org.example.contract.request.CreateTransRequest;
import org.example.contract.response.CreateTransResponse;
import org.example.mappers.DomainTransMapper;
import org.example.model.Transportation;
import org.example.validators.CreateTransValidator;

import java.time.LocalDateTime;

public class CreateTrans {

    private final CreateTransValidator validator;
    private final DomainTransMapper domainTransMapper;
    private final TransRepo transRepo;

    public CreateTrans(CreateTransValidator validator, DomainTransMapper domainTransMapper, TransRepo transRepo) {
        this.validator = validator;
        this.domainTransMapper = domainTransMapper;
        this.transRepo = transRepo;
    }

    public CreateTransResponse execute(CreateTransRequest request){

        validator.validate(request);
        final Transportation transportation = domainTransMapper.toDomain(request);
        addSystemValues(transportation);
        final Transportation save = transRepo.save(transportation);
        return domainTransMapper.toResponse(save);
    }

    public void addSystemValues(Transportation transportation){
        transportation.setCreatedAt(LocalDateTime.now());
    }
}
