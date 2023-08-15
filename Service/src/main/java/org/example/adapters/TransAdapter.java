package org.example.adapters;

import org.example.contract.repository.TransRepo;
import org.example.mappers.TransMapper;
import org.example.model.Transportation;
import org.example.repositories.TransRepoJpa;
import org.example.entities.TransportationEntity;

import java.util.Optional;

public class TransAdapter implements TransRepo {

    private final TransRepoJpa transRepoJpa;
    private final TransMapper transMapper;

    public TransAdapter(TransRepoJpa transRepoJpa, TransMapper transMapper) {
        this.transRepoJpa = transRepoJpa;
        this.transMapper = transMapper;
    }

    @Override
    public Transportation save(Transportation transportation) {

        final TransportationEntity transportationEntity = transMapper.domainToEntity(transportation);
        final TransportationEntity save = transRepoJpa.save(transportationEntity);

        return transMapper.entityToDomain(save);
    }

    @Override
    public Optional<Transportation> findById(Long id) {
        final Optional<TransportationEntity> byId = transRepoJpa.findById(id);
        final TransportationEntity transportationEntity = byId.orElse(null);
        final Transportation transportation = transMapper.entityToDomain(transportationEntity);
        return Optional.ofNullable(transportation);
    }

    @Override
    public void delete(Transportation transportation) {
        transRepoJpa.delete(transMapper.domainToEntity(transportation));
    }
}
