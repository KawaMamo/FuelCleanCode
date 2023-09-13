package org.example.adapters;

import org.example.contract.repository.RegionRepo;
import org.example.mappers.RegionMapper;
import org.example.model.Region;
import org.example.repositories.RegionRepository;
import org.example.entities.RegionEntity;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

public class RegionAdapter implements RegionRepo {

    private final RegionRepository repository;
    private final RegionMapper mapper;

    public RegionAdapter(RegionRepository repository, RegionMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Region save(Region region) {
        final RegionEntity regionEntity = mapper.domainToEntity(region);
        regionEntity.setCreatedAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        final RegionEntity save = repository.save(regionEntity);
        return mapper.entityToDomain(save);
    }

    @Override
    public Optional<Region> findById(Long id) {
        final Optional<RegionEntity> byId = repository.findById(id);
        final RegionEntity regionEntity = byId.orElse(null);
        final Region region = mapper.entityToDomain(regionEntity);
        return Optional.ofNullable(region);
    }

    @Override
    public void delete(Region region) {
        repository.delete(mapper.domainToEntity(region));
    }
}
