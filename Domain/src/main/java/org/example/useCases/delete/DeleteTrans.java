package org.example.useCases.delete;

import org.example.contract.repository.TransRepo;
import org.example.contract.response.TransResponse;
import org.example.mappers.DomainTransMapper;
import org.example.model.Transportation;

import java.util.NoSuchElementException;

public class DeleteTrans {
    private final TransRepo transRepo;
    private final DomainTransMapper mapper;

    public DeleteTrans(TransRepo transRepo, DomainTransMapper mapper) {
        this.transRepo = transRepo;
        this.mapper = mapper;
    }

    public TransResponse execute(Long id){
        final Transportation transportation = transRepo.findById(id).orElseThrow(NoSuchElementException::new);
        transRepo.delete(transportation);
        return mapper.toResponse(transportation);
    }
}
