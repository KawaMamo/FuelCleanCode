package org.example.adapters;

import org.example.contract.repository.OfficePaymentRepo;
import org.example.mappers.OfficePaymentMapper;
import org.example.model.OfficePayment;
import org.example.repositories.OfficePaymentRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.NoSuchElementException;
import java.util.Optional;

public class OfficePaymentAdapter implements OfficePaymentRepo {
    private final OfficePaymentRepository repository;
    private final OfficePaymentMapper mapper;

    public OfficePaymentAdapter(OfficePaymentRepository repository, OfficePaymentMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public OfficePayment save(OfficePayment payment) {
        final org.example.entities.OfficePayment entity = mapper.domainToEntity(payment);
        entity.setCreatedAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        final org.example.entities.OfficePayment save = repository.save(entity);
        return mapper.entityToDomain(save);
    }

    @Override
    public Optional<OfficePayment> findById(Long id) {
        final org.example.entities.OfficePayment officePayment = repository.findById(id).orElseThrow(NoSuchElementException::new);
        return Optional.ofNullable(mapper.entityToDomain(officePayment));
    }

    @Override
    public void delete(OfficePayment payment) {
        repository.delete(mapper.domainToEntity(payment));
    }
}
