package org.example.useCases.update;

import org.example.contract.repository.RefineryRepo;
import org.example.contract.request.update.UpdateRefineryRequest;
import org.example.contract.response.RefineryResponse;
import org.example.mappers.RefineryDomainMapper;
import org.example.model.Refinery;
import org.example.validators.update.UpdateRefineryValidator;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.NoSuchElementException;

public class UpdateRefinery {
    private final UpdateRefineryValidator validator;
    private final RefineryRepo refineryRepo;
    private final RefineryDomainMapper mapper;

    public UpdateRefinery(UpdateRefineryValidator validator,
                          RefineryRepo refineryRepo,
                          RefineryDomainMapper mapper) {
        this.validator = validator;
        this.refineryRepo = refineryRepo;
        this.mapper = mapper;
    }

    public RefineryResponse execute(UpdateRefineryRequest request){
        final Refinery original = refineryRepo.findById(request.getId()).orElseThrow(NoSuchElementException::new);
        validator.validate(request);
        final Refinery refinery = mapper.toDomain(request);
        refinery.setCreatedAt(original.getCreatedAt());
        refinery.setUpdatedAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        return mapper.toResponse(refinery);
    }
}
