package org.example.useCases.delete;

import org.example.contract.repository.ClientPaymentRepo;
import org.example.contract.response.ClientPaymentResponse;
import org.example.mappers.ClientPaymentDomainMapper;
import org.example.model.ClientPayment;

import java.util.NoSuchElementException;

public class DeleteClientPayment {
    private final ClientPaymentRepo repo;
    private final ClientPaymentDomainMapper mapper;

    public DeleteClientPayment(ClientPaymentRepo repo, ClientPaymentDomainMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }
    public ClientPaymentResponse delete(Long id) {
        final ClientPayment clientPayment = repo.findById(id).orElseThrow(NoSuchElementException::new);
        repo.delete(clientPayment);
        return mapper.domainToResponse(clientPayment);
    }
}
