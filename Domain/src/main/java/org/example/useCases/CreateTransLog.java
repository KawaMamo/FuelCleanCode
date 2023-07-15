package org.example.useCases;

import org.example.contract.repository.TransLogRepo;
import org.example.contract.request.CreateTransLogRequest;
import org.example.mappers.TransLogDomainMapper;
import org.example.model.TransLog;
import org.example.validators.CreateTransLogValidator;

import java.time.LocalDateTime;

public class CreateTransLog {
    private final TransLogRepo transLogRepo;
    public final TransLogDomainMapper transLogDomainMapper;
    private final CreateTransLogValidator validator;

    public CreateTransLog(TransLogRepo transLogRepo,
                          TransLogDomainMapper transLogDomainMapper,
                          CreateTransLogValidator validator) {
        this.transLogRepo = transLogRepo;
        this.transLogDomainMapper = transLogDomainMapper;
        this.validator = validator;
    }

    public TransLog execute(CreateTransLogRequest request){
        validator.validate(request);
        final TransLog transLog = transLogDomainMapper.requestToDomain(request);
        transLog.setCreatedAt(LocalDateTime.now());
        final TransLog save = transLogRepo.save(transLog);
        return transLogDomainMapper.domainToResponse(save);
    }
}
