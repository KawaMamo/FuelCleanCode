package org.example.useCases.delete;

import org.example.contract.repository.RefineryRepo;
import org.example.contract.response.RefineryResponse;
import org.example.mappers.RefineryDomainMapper;
import org.example.model.Refinery;

import java.util.NoSuchElementException;

public class DeleteRefinery {
    private final RefineryRepo refineryRepo;
    private final RefineryDomainMapper mapper;
    public DeleteRefinery(RefineryRepo refineryRepo, RefineryDomainMapper mapper) {
        this.refineryRepo = refineryRepo;
        this.mapper = mapper;
    }
    public RefineryResponse execute(Long id){
        final Refinery refinery = refineryRepo.findById(id).orElseThrow(NoSuchElementException::new);
        refineryRepo.delete(refinery);
        return mapper.toResponse(refinery);
    }
}
