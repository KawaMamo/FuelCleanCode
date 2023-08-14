package org.example.useCases.update;


import org.example.contract.repository.TransRepo;
import org.example.contract.request.update.UpdateTransRequest;
import org.example.contract.response.CreateTransResponse;
import org.example.mappers.DomainTransMapper;
import org.example.model.Transportation;
import org.example.validators.update.UpdateTransValidator;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

public class UpdateTrans {
    public final UpdateTransValidator validator;
    private final DomainTransMapper mapper;
    private final TransRepo transRepo;

    public UpdateTrans(UpdateTransValidator validator, DomainTransMapper mapper, TransRepo transRepo) {
        this.validator = validator;
        this.mapper = mapper;
        this.transRepo = transRepo;
    }

    public CreateTransResponse execute(UpdateTransRequest request){
        final Transportation original = transRepo.findById(request.getId()).orElseThrow(NoSuchElementException::new);
        validator.validate(request);
        final Transportation transportation = mapper.toDomain(request);
        transportation.setCreatedAt(original.getCreatedAt());
        transportation.setUpdatedAt(LocalDateTime.now());
        final Transportation save = transRepo.save(transportation);
        return mapper.toResponse(save);
    }
}
