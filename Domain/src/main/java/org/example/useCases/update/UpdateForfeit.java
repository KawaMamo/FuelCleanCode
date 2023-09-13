package org.example.useCases.update;

import org.example.contract.repository.ForfeitRepo;
import org.example.contract.request.update.UpdateForfeitRequest;
import org.example.contract.response.ForfeitResponse;
import org.example.mappers.ForfeitDomainMapper;
import org.example.model.Forfeit;
import org.example.validators.update.UpdateForfeitValidator;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.NoSuchElementException;

public class UpdateForfeit {
    private final UpdateForfeitValidator validator;
    private final ForfeitDomainMapper forfeitDomainMapper;
    private final ForfeitRepo forfeitRepo;

    public UpdateForfeit(UpdateForfeitValidator validator,
                         ForfeitDomainMapper forfeitDomainMapper,
                         ForfeitRepo forfeitRepo) {
        this.validator = validator;
        this.forfeitDomainMapper = forfeitDomainMapper;
        this.forfeitRepo = forfeitRepo;
    }

    public ForfeitResponse execute(UpdateForfeitRequest request){
        final Forfeit original = forfeitRepo.findById(request.getId()).orElseThrow(NoSuchElementException::new);
        validator.validate(request);
        final Forfeit domain = forfeitDomainMapper.requestToDomain(request);
        domain.setCreatedAt(original.getCreatedAt());
        domain.setUpdatedAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        final Forfeit save = forfeitRepo.save(domain);
        return forfeitDomainMapper.domainToResponse(save);
    }
}
