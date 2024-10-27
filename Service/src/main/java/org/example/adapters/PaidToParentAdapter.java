package org.example.adapters;

import org.example.contract.repository.PaidToParentRepo;
import org.example.mappers.PaidToParentMapper;
import org.example.model.PaidToParent;
import org.example.repositories.PaidToParentRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.NoSuchElementException;
import java.util.Optional;

public class PaidToParentAdapter implements PaidToParentRepo {
    private final PaidToParentRepository repository;
    private final PaidToParentMapper mapper;

    public PaidToParentAdapter(PaidToParentRepository repository, PaidToParentMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public PaidToParent save(PaidToParent paidToParent) {
        final org.example.entities.PaidToParent entity = mapper.domainToEntity(paidToParent);
        entity.setCreatedAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        final org.example.entities.PaidToParent save = repository.save(entity);
        return mapper.entityToDomain(save);
    }

    @Override
    public Optional<PaidToParent> findById(Long id) {
        final org.example.entities.PaidToParent paidToParent = repository.findById(id).orElseThrow(NoSuchElementException::new);
        return Optional.ofNullable(mapper.entityToDomain(paidToParent));
    }

    @Override
    public void delete(PaidToParent paidToParent) {
        repository.delete(mapper.domainToEntity(paidToParent));
    }
}
