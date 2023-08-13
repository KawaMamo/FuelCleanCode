package org.example.adapters;

import org.example.contract.repository.MaterialRepo;
import org.example.mappers.MaterialMapper;
import org.example.model.Material;
import org.example.repositories.MaterialRepository;
import org.example.entities.MaterialEntity;

import java.time.LocalDateTime;
import java.util.Optional;

public class MaterialAdapter implements MaterialRepo {

    private final MaterialRepository repository;
    private final MaterialMapper mapper;

    public MaterialAdapter(MaterialRepository repository, MaterialMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Material save(Material material) {
        final MaterialEntity materialEntity = mapper.domainToEntity(material);
        final MaterialEntity save = repository.save(materialEntity);
        return mapper.entityToDomain(save);
    }

    @Override
    public Optional<Material> findById(Long id) {
        final Optional<MaterialEntity> byId = repository.findById(id);
        final MaterialEntity materialEntity = byId.orElse(null);
        final Material material = mapper.entityToDomain(materialEntity);
        return Optional.ofNullable(material);
    }
}
