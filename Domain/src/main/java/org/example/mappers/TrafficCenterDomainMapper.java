package org.example.mappers;

import org.example.contract.request.create.CreateTrafficCenterRequest;
import org.example.contract.request.update.UpdateTrafficCenterRequest;
import org.example.contract.response.TrafficCenterResponse;
import org.example.model.TrafficCenter;
import org.mapstruct.Mapper;

@Mapper
public interface TrafficCenterDomainMapper {
    TrafficCenter requestToDomain(CreateTrafficCenterRequest request);
    TrafficCenterResponse domainToResponse(TrafficCenter trafficCenter);
    TrafficCenter requestToDomain(UpdateTrafficCenterRequest request);
}
