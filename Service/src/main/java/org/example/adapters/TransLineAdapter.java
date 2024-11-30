package org.example.adapters;

import org.example.contract.repository.TransLineRepo;
import org.example.entities.TransLineEntity;
import org.example.mappers.TransLineMapper;
import org.example.model.GasStation;
import org.example.model.TransLine;
import org.example.repositories.TransLineRepository;

import java.util.Optional;

public class TransLineAdapter implements TransLineRepo {

    private final TransLineRepository transLineRepository;
    private final TransLineMapper transLineMapper;

    public TransLineAdapter(TransLineRepository transLineRepository, TransLineMapper transLineMapper) {
        this.transLineRepository = transLineRepository;
        this.transLineMapper = transLineMapper;
    }

    @Override
    public TransLine save(TransLine transLine) {
        final TransLineEntity transLineEntity = transLineMapper.domainToEntity(transLine);
        final TransLineEntity save = transLineRepository.save(transLineEntity);
        return transLineMapper.entityToDomain(save);
    }

    @Override
    public Optional<TransLine> findById(Long id) {
        final Optional<TransLineEntity> byId = transLineRepository.findById(id);
        final TransLineEntity transLineEntity = byId.orElse(null);
        final TransLine transLine = transLineMapper.entityToDomain(transLineEntity);
        return Optional.ofNullable(transLine);
    }

    @Override
    public void delete(TransLine transLine) {
        transLineRepository.delete(transLineMapper.domainToEntity(transLine));
    }

    @Override
    public Optional<TransLine> findBySourceAndDestination(GasStation source, GasStation destination) {
        final TransLineEntity transLineEntity = transLineRepository.findBySourceAndDestination(source, destination).orElseThrow();
        return Optional.of(transLineMapper.entityToDomain(transLineEntity));
    }
}
