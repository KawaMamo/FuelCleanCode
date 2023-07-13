package org.example.adapters;

import org.example.contract.repository.RefineryRepo;
import org.example.entities.PlaceEntity;
import org.example.mappers.RefineryMapper;
import org.example.model.Refinery;
import org.example.repositories.RefineryRepository;
import org.example.entities.RefineryEntity;

import java.time.LocalDateTime;
import java.util.Optional;

public class RefineryAdapter implements RefineryRepo {

    private final RefineryRepository repository;
    private final RefineryMapper mapper;

    public RefineryAdapter(RefineryRepository repository, RefineryMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Refinery save(Refinery refinery) {
        final RefineryEntity refineryEntity = mapper.toEntity(refinery);
        refineryEntity.setPlaceType("Refinery");
        refineryEntity.setCreatedAt(LocalDateTime.now());
        final RefineryEntity save = repository.save(refineryEntity);
        return mapper.toDomain(save);
    }

    @Override
    public Optional<Refinery> findById(Long id) {
        final Optional<PlaceEntity> byId = repository.findById(id);
        final RefineryEntity refineryEntity = (RefineryEntity) byId.orElse(null);
        final Refinery refinery = mapper.toDomain(refineryEntity);
        return Optional.ofNullable(refinery);
    }
}
