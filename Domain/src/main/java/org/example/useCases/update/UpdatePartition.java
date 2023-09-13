package org.example.useCases.update;

import org.example.contract.repository.PartitionRepo;
import org.example.contract.request.update.UpdatePartitionRequest;
import org.example.contract.response.PartitionResponse;
import org.example.mappers.PartitionDomainMapper;
import org.example.model.Partition;
import org.example.validators.update.UpdatePartitionValidator;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.NoSuchElementException;

public class UpdatePartition {
    private final UpdatePartitionValidator validator;
    private final PartitionDomainMapper mapper;
    private final PartitionRepo partitionRepo;

    public UpdatePartition(UpdatePartitionValidator validator,
                           PartitionDomainMapper mapper,
                           PartitionRepo partitionRepo) {
        this.validator = validator;
        this.mapper = mapper;
        this.partitionRepo = partitionRepo;
    }

    public PartitionResponse execute(UpdatePartitionRequest request){
        final Partition original = partitionRepo.findById(request.getId()).orElseThrow(NoSuchElementException::new);
        validator.validate(request);
        final Partition partition = mapper.requestToDomain(request);
        partition.setCreatedAt(original.getCreatedAt());
        partition.setUpdatedAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        final Partition save = partitionRepo.save(partition);
        return mapper.domainToResponse(save);
    }
}
