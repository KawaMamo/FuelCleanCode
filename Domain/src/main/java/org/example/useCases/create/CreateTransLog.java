package org.example.useCases.create;

import org.example.contract.repository.*;
import org.example.contract.request.create.CreateTransLogRequest;
import org.example.contract.request.create.FatCreateTransLogRequest;
import org.example.contract.response.TransLogResponse;
import org.example.mappers.TransLogDomainMapper;
import org.example.mappers.TransportationTypeDetector;
import org.example.model.TransLog;
import org.example.model.TransferMaterials;
import org.example.model.Transportation;
import org.example.model.TransportationReason;
import org.example.validators.create.CreateTransLogValidator;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class CreateTransLog {
    private final TransLogRepo transLogRepo;
    public final TransLogDomainMapper transLogDomainMapper;
    private final CreateTransLogValidator validator;
    private final VehicleRepo vehicleRepo;
    private final TransLineRepo transLineRepo;
    private final TransRepo transRepo;
    private final TransferMaterialRepo transferMaterialRepo;
    private final TransportationTypeDetector transportationTypeDetector;

    public CreateTransLog(TransLogRepo transLogRepo,
                          TransLogDomainMapper transLogDomainMapper,
                          CreateTransLogValidator validator, VehicleRepo vehicleRepo, TransLineRepo transLineRepo, TransRepo transRepo, TransferMaterialRepo transferMaterialRepo, TransportationTypeDetector transportationTypeDetector) {
        this.transLogRepo = transLogRepo;
        this.transLogDomainMapper = transLogDomainMapper;
        this.validator = validator;
        this.vehicleRepo = vehicleRepo;
        this.transLineRepo = transLineRepo;
        this.transRepo = transRepo;
        this.transferMaterialRepo = transferMaterialRepo;
        this.transportationTypeDetector = transportationTypeDetector;
    }

    public TransLogResponse execute(CreateTransLogRequest request){
        validator.validate(request);
        final TransportationReason reason = transportationTypeDetector.detect(request.getTransportationReasonId());
        final FatCreateTransLogRequest fatCreateTransLogRequest = new FatCreateTransLogRequest();
        extracted(request, reason, fatCreateTransLogRequest);
        final TransLog transLog = transLogDomainMapper.requestToDomain(fatCreateTransLogRequest);
        transLog.setCreatedAt(((Transportation) reason).getCreatedAt());
        final TransLog save = transLogRepo.save(transLog);
        vehicleRepo.findById(save.getVehicle().getId()).ifPresent(save::setVehicle);
        transLineRepo.findById(save.getTransLine().getId()).ifPresent(save::setTransLine);
        if(reason instanceof Transportation){
            transRepo.findById(save.getTransportation().getId()).ifPresent(save::setTransportation);
        }else if(reason instanceof TransferMaterials){
            transferMaterialRepo.findById(save.getTransportation().getId()).ifPresent(save::setTransportation);
        }
        return transLogDomainMapper.domainToResponse(save);
    }

    private static void extracted(CreateTransLogRequest request, TransportationReason reason, FatCreateTransLogRequest fatCreateTransLogRequest) {
        fatCreateTransLogRequest.setTransLineId(request.getTransLineId());
        fatCreateTransLogRequest.setFees(request.getFees());
        fatCreateTransLogRequest.setNotes(request.getNotes());
        fatCreateTransLogRequest.setVehicleId(request.getVehicleId());
        fatCreateTransLogRequest.setTransportation(reason);
    }
}
