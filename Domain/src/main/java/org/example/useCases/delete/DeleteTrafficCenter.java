package org.example.useCases.delete;

import org.example.contract.repository.TrafficCenterRepo;
import org.example.contract.response.TrafficCenterResponse;
import org.example.mappers.TrafficCenterDomainMapper;
import org.example.model.TrafficCenter;

import java.util.NoSuchElementException;

public class DeleteTrafficCenter {
    private final TrafficCenterRepo trafficCenterRepo;
    private final TrafficCenterDomainMapper mapper;
    public DeleteTrafficCenter(TrafficCenterRepo trafficCenterRepo, TrafficCenterDomainMapper mapper) {
        this.trafficCenterRepo = trafficCenterRepo;
        this.mapper = mapper;
    }
    public TrafficCenterResponse execute(Long id){
        TrafficCenter trafficCenter = trafficCenterRepo.findById(id).orElseThrow(NoSuchElementException::new);
        trafficCenterRepo.delete(trafficCenter);
        return mapper.domainToResponse(trafficCenter);
    }
}
