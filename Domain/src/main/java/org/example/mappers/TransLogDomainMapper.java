package org.example.mappers;

import org.example.contract.request.create.CreateTransLogRequest;
import org.example.contract.request.create.FatCreateTransLogRequest;
import org.example.contract.request.update.UpdateTransLogRequest;
import org.example.contract.response.TransLogResponse;
import org.example.model.TransLog;
import org.example.model.TransportationReason;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper
public interface TransLogDomainMapper {
    @Mapping(source = "vehicleId", target = "vehicle.id")
    @Mapping(source = "transLineId", target = "transLine.id")
    TransLog requestToDomain(FatCreateTransLogRequest request);
    TransLogResponse domainToResponse(TransLog transLog);
    @Mapping(source = "vehicleId", target = "vehicle.id")
    @Mapping(source = "transLineId", target = "transLine.id")
    TransLog requestToDomain(UpdateTransLogRequest request);
}
