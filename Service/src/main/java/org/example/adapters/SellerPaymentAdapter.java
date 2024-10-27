package org.example.adapters;

import org.example.contract.repository.SellerPaymentRepo;
import org.example.mappers.SellerPaymentMapper;
import org.example.model.SellerPayment;
import org.example.repositories.SellerPaymentRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.NoSuchElementException;
import java.util.Optional;

public class SellerPaymentAdapter implements SellerPaymentRepo {
    private final SellerPaymentRepository repository;
    private final SellerPaymentMapper mapper;

    public SellerPaymentAdapter(SellerPaymentRepository repository, SellerPaymentMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public SellerPayment save(SellerPayment payment) {
        final org.example.entities.SellerPayment entity = mapper.domainToEntity(payment);
        entity.setCreatedAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        final org.example.entities.SellerPayment save = repository.save(entity);
        return mapper.entityToDomain(save);
    }

    @Override
    public Optional<SellerPayment> findById(Long id) {
        final org.example.entities.SellerPayment sellerPayment = repository.findById(id).orElseThrow(NoSuchElementException::new);
        return Optional.ofNullable(mapper.entityToDomain(sellerPayment));
    }

    @Override
    public void delete(SellerPayment payment) {
        repository.delete(mapper.domainToEntity(payment));
    }
}
