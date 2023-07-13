package org.example.useCases;

import org.example.contract.repository.PartitionRepo;
import org.example.contract.repository.TransRepo;
import org.example.contract.request.CreatePartitionRequest;
import org.example.contract.response.PartitionResponse;
import org.example.mappers.PartitionDomainMapper;
import org.example.model.Partition;
import org.example.model.Transportation;
import org.example.validators.CreatePartitionValidator;

import java.time.LocalDateTime;
import java.util.Optional;

public class CreatePartition {
    private final CreatePartitionValidator validator;
    private final PartitionDomainMapper mapper;
    private final PartitionRepo partitionRepo;
    private final TransRepo transRepo;

    public CreatePartition(CreatePartitionValidator validator, PartitionDomainMapper mapper, PartitionRepo partitionRepo, TransRepo transRepo) {
        this.validator = validator;
        this.mapper = mapper;
        this.partitionRepo = partitionRepo;
        this.transRepo = transRepo;
    }

    public PartitionResponse execute(CreatePartitionRequest request){
        validator.validate(request);
        final Partition partition = mapper.requestToDomain(request);
        partition.setCreatedAt(LocalDateTime.now());
        final Partition save = partitionRepo.save(partition);
        final Optional<Transportation> byId = transRepo.findById(save.getTransportation().getId());
        byId.ifPresent(save::setTransportation);
        return mapper.domainToResponse(save);
    }
}

