package org.example.mappers;

import org.example.contract.request.create.CreateTransLogRequest;
import org.example.contract.request.update.UpdateTransLogRequest;
import org.example.contract.response.TransLogResponse;
import org.example.model.TransLog;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface TransLogDomainMapper {
    @Mapping(source = "vehicleId", target = "vehicle.id")
    @Mapping(source = "transLineId", target = "transLine.id")
    @Mapping(source = "transportationId", target = "transportation.id")
    TransLog requestToDomain(CreateTransLogRequest request);
    TransLogResponse domainToResponse(TransLog transLog);
    @Mapping(source = "vehicleId", target = "vehicle.id")
    @Mapping(source = "transLineId", target = "transLine.id")
    @Mapping(source = "transportationId", target = "transportation.id")
    TransLog requestToDomain(UpdateTransLogRequest request);
}
