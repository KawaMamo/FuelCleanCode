package org.example.useCases.update;

import org.example.contract.repository.TransLineRepo;
import org.example.contract.request.update.UpdateTransLineRequest;
import org.example.contract.response.TransLineResponse;
import org.example.mappers.TransLineDomainMapper;
import org.example.model.TransLine;
import org.example.validators.update.UpdateTransLineValidator;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.NoSuchElementException;

public class UpdateTransLine {
    private final UpdateTransLineValidator validator;
    private final TransLineDomainMapper mapper;
    private final TransLineRepo transLineRepo;

    public UpdateTransLine(UpdateTransLineValidator validator,
                           TransLineDomainMapper mapper,
                           TransLineRepo transLineRepo) {
        this.validator = validator;
        this.mapper = mapper;
        this.transLineRepo = transLineRepo;
    }

    public TransLineResponse execute(UpdateTransLineRequest request) {
        final TransLine original = transLineRepo.findById(request.getId()).orElseThrow(NoSuchElementException::new);
        validator.validate(request);
        final TransLine transLine = mapper.requestToDomain(request);
        transLine.setCreatedAt(original.getCreatedAt());
        transLine.setUpdatedAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        transLineRepo.save(transLine);
        return mapper.domainToResponse(transLineRepo.save(transLine));
    }
}
