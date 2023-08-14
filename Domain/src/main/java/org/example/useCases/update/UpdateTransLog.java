package org.example.useCases.update;

import org.example.contract.repository.TransLogRepo;
import org.example.contract.request.update.UpdateTransLogRequest;
import org.example.contract.response.TransLogResponse;
import org.example.mappers.TransLogDomainMapper;
import org.example.model.TransLog;
import org.example.validators.update.UpdateTransLogValidator;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

public class UpdateTransLog {
    private final UpdateTransLogValidator validator;
    private final TransLogRepo transLogRepo;
    private final TransLogDomainMapper mapper;

    public UpdateTransLog(UpdateTransLogValidator validator, TransLogRepo transLogRepo, TransLogDomainMapper mapper) {
        this.validator = validator;
        this.transLogRepo = transLogRepo;
        this.mapper = mapper;
    }
    public TransLogResponse execute(UpdateTransLogRequest request) {
        final TransLog original = transLogRepo.findById(request.getId()).orElseThrow(NoSuchElementException::new);
        validator.validate(request);
        final TransLog transLog = mapper.requestToDomain(request);
        transLog.setCreatedAt(original.getCreatedAt());
        transLog.setUpdatedAt(LocalDateTime.now());
        transLogRepo.save(transLog);
        return mapper.domainToResponse(transLog);
    }
}
