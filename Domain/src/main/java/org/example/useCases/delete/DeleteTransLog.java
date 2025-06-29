package org.example.useCases.delete;

import org.example.contract.repository.TransLogRepo;
import org.example.contract.response.TransLogResponse;
import org.example.mappers.TransLogDomainMapper;
import org.example.model.TransLog;

import java.util.NoSuchElementException;

public class DeleteTransLog {
    private final TransLogDomainMapper mapper;
    private final TransLogRepo transLogRepo;

    public DeleteTransLog(TransLogDomainMapper mapper, TransLogRepo transLogRepo) {
        this.mapper = mapper;
        this.transLogRepo = transLogRepo;
    }

    public TransLogResponse execute(Long id){
        TransLog transLog = transLogRepo.findById(id).orElseThrow(NoSuchElementException::new);
        transLogRepo.delete(transLog);
        return mapper.domainToResponse(transLog);
    }
}
