package org.example.useCases.delete;

import org.example.contract.repository.OfficePaymentRepo;
import org.example.contract.response.OfficePaymentResponse;
import org.example.mappers.OfficePaymentDomainMapper;
import org.example.model.OfficePayment;

import java.util.NoSuchElementException;

public class DeleteOfficePayment {
    private final OfficePaymentDomainMapper mapper;
    private final OfficePaymentRepo repo;

    public DeleteOfficePayment(OfficePaymentDomainMapper mapper, OfficePaymentRepo repo) {
        this.mapper = mapper;
        this.repo = repo;
    }
    public OfficePaymentResponse delete(Long id) {
        final OfficePayment officePayment = repo.findById(id).orElseThrow(NoSuchElementException::new);
        repo.delete(officePayment);
        return mapper.domainToResponse(officePayment);
    }
}
