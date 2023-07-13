package org.example.adapters;

import org.example.contract.repository.PartitionRepo;
import org.example.entities.PartitionEntity;
import org.example.mappers.PartitionMapper;
import org.example.model.Partition;
import org.example.repositories.PartitionRepository;

import java.util.Optional;

public class PartitionAdapter implements PartitionRepo {
    private final PartitionRepository partitionRepository;
    private final PartitionMapper mapper;

    public PartitionAdapter(PartitionRepository partitionRepository, PartitionMapper mapper) {
        this.partitionRepository = partitionRepository;
        this.mapper = mapper;
    }

    @Override
    public Partition save(Partition partition) {
        final PartitionEntity partitionEntity = mapper.domainToEntity(partition);
        final PartitionEntity save = partitionRepository.save(partitionEntity);
        return mapper.entityToDomain(save);
    }

    @Override
    public Optional<Partition> findById(Long id) {
        final Optional<PartitionEntity> byId = partitionRepository.findById(id);
        final PartitionEntity partitionEntity = byId.orElse(null);
        final Partition partition = mapper.entityToDomain(partitionEntity);
        return Optional.ofNullable(partition);
    }
}
