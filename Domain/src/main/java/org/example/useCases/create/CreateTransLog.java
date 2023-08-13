package org.example.useCases.create;

import org.example.contract.repository.TransLineRepo;
import org.example.contract.repository.TransLogRepo;
import org.example.contract.repository.TransRepo;
import org.example.contract.repository.VehicleRepo;
import org.example.contract.request.create.CreateTransLogRequest;
import org.example.contract.response.TransLogResponse;
import org.example.mappers.TransLogDomainMapper;
import org.example.model.TransLog;
import org.example.validators.create.CreateTransLogValidator;

import java.time.LocalDateTime;

public class CreateTransLog {
    private final TransLogRepo transLogRepo;
    public final TransLogDomainMapper transLogDomainMapper;
    private final CreateTransLogValidator validator;
    private final VehicleRepo vehicleRepo;
    private final TransLineRepo transLineRepo;
    private final TransRepo transRepo;

    public CreateTransLog(TransLogRepo transLogRepo,
                          TransLogDomainMapper transLogDomainMapper,
                          CreateTransLogValidator validator, VehicleRepo vehicleRepo, TransLineRepo transLineRepo, TransRepo transRepo) {
        this.transLogRepo = transLogRepo;
        this.transLogDomainMapper = transLogDomainMapper;
        this.validator = validator;
        this.vehicleRepo = vehicleRepo;
        this.transLineRepo = transLineRepo;
        this.transRepo = transRepo;
    }

    public TransLogResponse execute(CreateTransLogRequest request){
        validator.validate(request);
        final TransLog transLog = transLogDomainMapper.requestToDomain(request);
        transLog.setCreatedAt(LocalDateTime.now());
        final TransLog save = transLogRepo.save(transLog);
        vehicleRepo.findById(save.getVehicle().getId()).ifPresent(save::setVehicle);
        transLineRepo.findById(save.getTransLine().getId()).ifPresent(save::setTransLine);
        transRepo.findById(save.getTransportation().getId()).ifPresent(save::setTransportation);
        return transLogDomainMapper.domainToResponse(save);
    }
}
