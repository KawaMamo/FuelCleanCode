package org.example.useCases.delete;

import org.example.contract.repository.TransLineRepo;
import org.example.contract.response.TransLineResponse;
import org.example.mappers.TransLineDomainMapper;
import org.example.model.TransLine;

import java.util.NoSuchElementException;

public class DeleteTransLine {
    private final TransLineRepo repo;
    private final TransLineDomainMapper mapper;
    public DeleteTransLine(TransLineRepo repo, TransLineDomainMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }
    public TransLineResponse execute(Long id){
        final TransLine transLine = repo.findById(id).orElseThrow(NoSuchElementException::new);
        repo.delete(transLine);
        return mapper.domainToResponse(transLine);
    }
}
