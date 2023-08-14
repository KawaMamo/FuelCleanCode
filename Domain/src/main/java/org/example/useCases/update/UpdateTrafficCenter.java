package org.example.useCases.update;

import org.example.contract.repository.TrafficCenterRepo;
import org.example.contract.request.update.UpdateTrafficCenterRequest;
import org.example.contract.response.TrafficCenterResponse;
import org.example.mappers.TrafficCenterDomainMapper;
import org.example.model.TrafficCenter;
import org.example.validators.update.UpdateTrafficCenterValidator;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

public class UpdateTrafficCenter {
    private final UpdateTrafficCenterValidator validator;
    private final TrafficCenterDomainMapper mapper;
    private final TrafficCenterRepo trafficCenterRepo;

    public UpdateTrafficCenter(UpdateTrafficCenterValidator validator,
                               TrafficCenterDomainMapper mapper,
                               TrafficCenterRepo trafficCenterRepo) {
        this.validator = validator;
        this.mapper = mapper;
        this.trafficCenterRepo = trafficCenterRepo;
    }

    public TrafficCenterResponse execute(UpdateTrafficCenterRequest request){
        final TrafficCenter original = trafficCenterRepo.findById(request.getId()).orElseThrow(NoSuchElementException::new);
        validator.validate(request);
        final TrafficCenter trafficCenter = mapper.requestToDomain(request);
        trafficCenter.setCreatedAt(original.getCreatedAt());
        trafficCenter.setUpdatedAt(LocalDateTime.now());
        return mapper.domainToResponse(trafficCenterRepo.save(trafficCenter));
    }
}
