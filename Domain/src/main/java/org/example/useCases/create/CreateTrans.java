package org.example.useCases.create;

import org.example.contract.repository.RefineryRepo;
import org.example.contract.repository.TransRepo;
import org.example.contract.repository.VehicleRepo;
import org.example.contract.request.create.CreateTransRequest;
import org.example.contract.response.CreateTransResponse;
import org.example.mappers.DomainTransMapper;
import org.example.model.Transportation;
import org.example.validators.create.CreateTransValidator;

import java.time.LocalDateTime;

public class CreateTrans {

    private final CreateTransValidator validator;
    private final DomainTransMapper domainTransMapper;
    private final TransRepo transRepo;
    private final VehicleRepo vehicleRepo;
    private final RefineryRepo refineryRepo;

    public CreateTrans(CreateTransValidator validator, DomainTransMapper domainTransMapper, TransRepo transRepo, VehicleRepo vehicleRepo, RefineryRepo refineryRepo) {
        this.validator = validator;
        this.domainTransMapper = domainTransMapper;
        this.transRepo = transRepo;
        this.vehicleRepo = vehicleRepo;
        this.refineryRepo = refineryRepo;
    }

    public CreateTransResponse execute(CreateTransRequest request){

        validator.validate(request);
        final Transportation transportation = domainTransMapper.toDomain(request);
        addSystemValues(transportation);
        final Transportation save = transRepo.save(transportation);
        vehicleRepo.findById(save.getVehicle().getId()).ifPresent(save::setVehicle);
        refineryRepo.findById(save.getRefinery().getId()).ifPresent(save::setRefinery);
        return domainTransMapper.toResponse(save);
    }

    public void addSystemValues(Transportation transportation){
        transportation.setCreatedAt(LocalDateTime.now());
    }
}
