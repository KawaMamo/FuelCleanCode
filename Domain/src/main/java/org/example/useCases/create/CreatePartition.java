package org.example.useCases.create;

import org.example.contract.repository.GasStationRepo;
import org.example.contract.repository.MaterialRepo;
import org.example.contract.repository.PartitionRepo;
import org.example.contract.repository.TransRepo;
import org.example.contract.request.create.CreatePartitionRequest;
import org.example.contract.response.PartitionResponse;
import org.example.mappers.PartitionDomainMapper;
import org.example.model.Partition;
import org.example.validators.create.CreatePartitionValidator;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

public class CreatePartition {
    private final CreatePartitionValidator validator;
    private final PartitionDomainMapper mapper;
    private final PartitionRepo partitionRepo;
    private final TransRepo transRepo;
    private final GasStationRepo gasStationRepo;
    private final MaterialRepo materialRepo;

    public CreatePartition(CreatePartitionValidator validator,
                           PartitionDomainMapper mapper,
                           PartitionRepo partitionRepo,
                           TransRepo transRepo,
                           GasStationRepo gasStationRepo, MaterialRepo materialRepo) {
        this.validator = validator;
        this.mapper = mapper;
        this.partitionRepo = partitionRepo;
        this.transRepo = transRepo;
        this.gasStationRepo = gasStationRepo;
        this.materialRepo = materialRepo;
    }

    public PartitionResponse execute(CreatePartitionRequest request){
        validator.validate(request);
        final Partition partition = mapper.requestToDomain(request);
        partition.setCreatedAt(LocalDateTime.now());
        final Partition save = partitionRepo.save(partition);
        transRepo.findById(save.getTransportation().getId()).ifPresent(save::setTransportation);
        gasStationRepo.findById(save.getGasStation().getId()).ifPresentOrElse(save::setGasStation, NoSuchElementException::new);
        materialRepo.findById(save.getMaterial().getId()).ifPresent(save::setMaterial);
        return mapper.domainToResponse(save);
    }
}

