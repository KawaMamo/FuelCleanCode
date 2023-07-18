package org.example.adapters;

import org.example.contract.repository.TransferMaterialRepo;
import org.example.entities.TransferMaterialsEntity;
import org.example.mappers.TransferMaterialsMapper;
import org.example.model.TransferMaterials;
import org.example.repositories.TransferMaterialRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public class TransferMaterialsAdapter implements TransferMaterialRepo {
    private final TransferMaterialRepository repository;
    private final TransferMaterialsMapper mapper;

    public TransferMaterialsAdapter(TransferMaterialRepository repository,
                                    TransferMaterialsMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public TransferMaterials save(TransferMaterials transferMaterials) {
        final TransferMaterialsEntity transferMaterialsEntity = mapper.domainToEntity(transferMaterials);
        final TransferMaterialsEntity save = repository.save(transferMaterialsEntity);
        return mapper.entityToDomain(save);
    }

    @Override
    public Optional<TransferMaterials> findById(Long id) {
        final Optional<TransferMaterialsEntity> byId = repository.findById(id);
        return Optional.ofNullable(mapper.entityToDomain(byId.orElse(null)));
    }
}
