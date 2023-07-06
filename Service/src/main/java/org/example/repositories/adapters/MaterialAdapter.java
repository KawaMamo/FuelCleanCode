package org.example.repositories.adapters;

import org.example.contract.repository.MaterialRepo;
import org.example.mappers.MaterialMapper;
import org.example.model.Material;
import org.example.repositories.MaterialRepository;
import org.example.repositories.entities.MaterialEntity;

import java.time.LocalDateTime;

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
        materialEntity.setCreatedAt(LocalDateTime.now());
        final MaterialEntity save = repository.save(materialEntity);
        return mapper.EntityToDomain(save);
    }
}
