package org.example.useCases.create;

import org.example.contract.repository.TrafficCenterRepo;
import org.example.contract.request.create.CreateTrafficCenterRequest;
import org.example.contract.response.TrafficCenterResponse;
import org.example.mappers.TrafficCenterDomainMapper;
import org.example.model.TrafficCenter;
import org.example.validators.create.CreateTrafficCenterValidator;

import java.time.LocalDateTime;

public class CreateTrafficCenter {
    private final CreateTrafficCenterValidator validator;
    private final TrafficCenterDomainMapper mapper;
    private final TrafficCenterRepo trafficCenterRepo;

    public CreateTrafficCenter(CreateTrafficCenterValidator validator, TrafficCenterDomainMapper mapper, TrafficCenterRepo trafficCenterRepo) {
        this.validator = validator;
        this.mapper = mapper;
        this.trafficCenterRepo = trafficCenterRepo;
    }

    public TrafficCenterResponse execute(CreateTrafficCenterRequest request){
        validator.validate(request);
        final TrafficCenter trafficCenter = mapper.requestToDomain(request);
        trafficCenter.setCreatedAt(LocalDateTime.now());
        final TrafficCenter save = trafficCenterRepo.save(trafficCenter);
        return mapper.domainToResponse(save);
    }
}
