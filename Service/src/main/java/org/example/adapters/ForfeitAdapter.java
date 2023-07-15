package org.example.adapters;

import org.example.contract.repository.ForfeitRepo;
import org.example.entities.ForfeitEntity;
import org.example.mappers.ForfeitMapper;
import org.example.model.Forfeit;
import org.example.repositories.ForfeitRepository;

import java.util.Optional;

public class ForfeitAdapter implements ForfeitRepo {

    private final ForfeitRepository repository;
    private final ForfeitMapper mapper;

    public ForfeitAdapter(ForfeitRepository repository,
                          ForfeitMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Forfeit save(Forfeit forfeit) {
        final ForfeitEntity forfeitEntity = mapper.domainToEntity(forfeit);
        final ForfeitEntity save = repository.save(forfeitEntity);
        return mapper.entityToDomain(save);
    }

    @Override
    public Optional<Forfeit> findById(Long id) {
        final Optional<ForfeitEntity> byId = repository.findById(id);
        final ForfeitEntity forfeitEntity = byId.orElse(null);
        final Forfeit forfeit = mapper.entityToDomain(forfeitEntity);
        return Optional.ofNullable(forfeit);
    }
}
