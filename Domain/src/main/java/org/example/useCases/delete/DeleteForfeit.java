package org.example.useCases.delete;

import org.example.contract.repository.ForfeitRepo;
import org.example.contract.response.ForfeitResponse;
import org.example.mappers.ForfeitDomainMapper;
import org.example.model.Forfeit;

import java.util.NoSuchElementException;

public class DeleteForfeit {
    private final ForfeitRepo forfeitRepo;
    private final ForfeitDomainMapper mapper;
    public DeleteForfeit(ForfeitRepo forfeitRepo, ForfeitDomainMapper mapper) {
        this.forfeitRepo = forfeitRepo;
        this.mapper = mapper;
    }
    public ForfeitResponse execute(Long id){
        final Forfeit forfeit = forfeitRepo.findById(id).orElseThrow(NoSuchElementException::new);
        forfeitRepo.delete(forfeit);
        return mapper.domainToResponse(forfeit);
    }
}
