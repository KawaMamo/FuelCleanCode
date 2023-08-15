package org.example.useCases.delete;

import org.example.contract.repository.PartitionRepo;
import org.example.contract.response.PartitionResponse;
import org.example.mappers.PartitionDomainMapper;
import org.example.model.Partition;

import java.util.NoSuchElementException;

public class DeletePartition {
    private final PartitionRepo partitionRepo;
    private final PartitionDomainMapper mapper;
    public DeletePartition(PartitionRepo partitionRepo, PartitionDomainMapper mapper) {
        this.partitionRepo = partitionRepo;
        this.mapper = mapper;
    }
    public PartitionResponse execute(Long id){
        final Partition partition = partitionRepo.findById(id).orElseThrow(NoSuchElementException::new);
        partitionRepo.delete(partition);
        return mapper.domainToResponse(partition);
    }
}
