package org.example.useCases.create;

import org.example.contract.repository.ForfeitRepo;
import org.example.contract.repository.PartitionRepo;
import org.example.contract.repository.VehicleRepo;
import org.example.contract.request.create.CreateForfeitRequest;
import org.example.contract.response.ForfeitResponse;
import org.example.mappers.ForfeitDomainMapper;
import org.example.model.Forfeit;
import org.example.validators.create.CreateForfeitValidator;

import java.time.LocalDateTime;

public class CreateForfeit {
    private final ForfeitRepo forfeitRepo;
    private final ForfeitDomainMapper mapper;
    private final CreateForfeitValidator validator;
    private final VehicleRepo vehicleRepo;
    private final PartitionRepo partitionRepo;

    public CreateForfeit(ForfeitRepo forfeitRepo,
                         ForfeitDomainMapper mapper,
                         CreateForfeitValidator validator,
                         VehicleRepo vehicleRepo,
                         PartitionRepo partitionRepo) {
        this.forfeitRepo = forfeitRepo;
        this.mapper = mapper;
        this.validator = validator;
        this.vehicleRepo = vehicleRepo;
        this.partitionRepo = partitionRepo;
    }
    public ForfeitResponse execute(CreateForfeitRequest request){
        validator.validate(request);
        final Forfeit forfeit = mapper.requestToDomain(request);
        forfeit.setCreatedAt(LocalDateTime.now());
        final Forfeit save = forfeitRepo.save(forfeit);
        vehicleRepo.findById(save.getVehicles().getId()).ifPresent(save::setVehicles);
        partitionRepo.findById(save.getPartition().getId()).ifPresent(save::setPartition);
        return mapper.domainToResponse(save);
    }
}
