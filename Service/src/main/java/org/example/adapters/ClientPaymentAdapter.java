package org.example.adapters;

import org.example.contract.repository.ClientPaymentRepo;
import org.example.mappers.ClientPaymentMapper;
import org.example.model.ClientPayment;
import org.example.repositories.ClientPaymentRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.NoSuchElementException;
import java.util.Optional;

public class ClientPaymentAdapter implements ClientPaymentRepo {
    private final ClientPaymentRepository clientPaymentRepository;
    private final ClientPaymentMapper mapper;

    public ClientPaymentAdapter(ClientPaymentRepository clientPaymentRepository, ClientPaymentMapper mapper) {
        this.clientPaymentRepository = clientPaymentRepository;
        this.mapper = mapper;
    }

    @Override
    public ClientPayment save(ClientPayment payment) {
        final org.example.entities.ClientPayment entity = mapper.domainToEntity(payment);
        entity.setCreatedAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        final org.example.entities.ClientPayment save = clientPaymentRepository.save(entity);
        return mapper.entityToDomain(save);
    }

    @Override
    public Optional<ClientPayment> findById(Long id) {
        final org.example.entities.ClientPayment clientPayment = clientPaymentRepository.findById(id).orElseThrow(NoSuchElementException::new);
        final ClientPayment domain = mapper.entityToDomain(clientPayment);
        return Optional.ofNullable(domain);
    }

    @Override
    public void delete(ClientPayment payment) {
        clientPaymentRepository.delete(mapper.domainToEntity(payment));
    }
}
