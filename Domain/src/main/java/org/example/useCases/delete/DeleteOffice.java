package org.example.useCases.delete;

import org.example.contract.repository.OfficeRepo;
import org.example.contract.response.OfficeResponse;
import org.example.mappers.OfficeDomainMapper;
import org.example.model.Office;

import java.util.NoSuchElementException;

public class DeleteOffice {
    private final OfficeRepo officeRepo;
    private final OfficeDomainMapper mapper;
    public DeleteOffice(OfficeRepo officeRepo, OfficeDomainMapper mapper) {
        this.officeRepo = officeRepo;
        this.mapper = mapper;
    }
    public OfficeResponse execute(Long id){
        final Office office = officeRepo.findById(id).orElseThrow(NoSuchElementException::new);
        officeRepo.delete(office);
        return mapper.domainToResponse(office);
    }
}
