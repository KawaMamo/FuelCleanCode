package org.example.useCases.delete;

import org.example.contract.repository.TransRepo;
import org.example.contract.response.TransResponse;
import org.example.mappers.DomainTransMapper;
import org.example.model.Transportation;

import java.util.NoSuchElementException;

public class DeleteTransDocuments {

    private final TransRepo transRepo;
    private final DomainTransMapper mapper;

    public DeleteTransDocuments(TransRepo transRepo, DomainTransMapper mapper) {
        this.transRepo = transRepo;
        this.mapper = mapper;
    }

    public TransResponse execute(Long id){
        final Transportation transportation = transRepo.findByIdAndDeletedAt(id, null).orElseThrow(NoSuchElementException::new);
        transportation.setDocument(null);
        final Transportation save = transRepo.save(transportation);
        return mapper.toResponse(save);
    }
}
