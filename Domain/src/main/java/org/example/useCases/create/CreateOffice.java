package org.example.useCases.create;

import org.example.contract.repository.OfficeRepo;
import org.example.contract.request.create.CreateOfficeRequest;
import org.example.contract.response.OfficeResponse;
import org.example.mappers.OfficeDomainMapper;
import org.example.model.Office;
import org.example.validators.create.CreateOfficeValidator;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class CreateOffice {
    private final OfficeDomainMapper mapper;
    private final OfficeRepo repo;
    private final CreateOfficeValidator validator;

    public CreateOffice(OfficeDomainMapper mapper, OfficeRepo repo, CreateOfficeValidator validator) {
        this.mapper = mapper;
        this.repo = repo;
        this.validator = validator;
    }

    public OfficeResponse execute(CreateOfficeRequest request){
        validator.validate(request);
        final Office office = mapper.requestToDomain(request);
        office.setCreatedAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        final Office save = repo.save(office);
        return mapper.domainToResponse(save);
    }
}
