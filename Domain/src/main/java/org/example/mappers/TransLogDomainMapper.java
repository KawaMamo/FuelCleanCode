package org.example.mappers;

import org.example.contract.request.CreateTransLogRequest;
import org.example.contract.response.TransLogResponse;
import org.example.model.TransLog;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface TransLogDomainMapper {
    TransLog requestToDomain(CreateTransLogRequest request);
    TransLogResponse domainToResponse(TransLog transLog);
}
