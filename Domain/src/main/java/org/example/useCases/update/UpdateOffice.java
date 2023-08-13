package org.example.useCases.update;

import org.example.contract.repository.OfficeRepo;
import org.example.contract.request.update.UpdateOfficeRequest;
import org.example.contract.response.OfficeResponse;
import org.example.mappers.OfficeDomainMapper;
import org.example.model.Office;
import org.example.validators.update.UpdateOfficeValidator;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

public class UpdateOffice {

    private final UpdateOfficeValidator validator;
    private final OfficeDomainMapper officeDomainMapper;
    private final OfficeRepo officeRepo;

    public UpdateOffice(UpdateOfficeValidator validator,
                        OfficeDomainMapper officeDomainMapper,
                        OfficeRepo officeRepo) {
        this.validator = validator;
        this.officeDomainMapper = officeDomainMapper;
        this.officeRepo = officeRepo;
    }

    public OfficeResponse execute(UpdateOfficeRequest request){
        final Office original = officeRepo.findById(request.getId()).orElseThrow(NoSuchElementException::new);
        validator.validate(request);
        final Office office = officeDomainMapper.requestToDomain(request);
        office.setCreatedAt(original.getCreatedAt());
        office.setUpdateAt(LocalDateTime.now());
        final Office save = officeRepo.save(office);
        return officeDomainMapper.domainToResponse(save);
    }
}
