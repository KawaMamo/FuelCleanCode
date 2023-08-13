package org.example.mappers;

import org.example.contract.request.create.CreateOfficeRequest;
import org.example.contract.request.update.UpdateOfficeRequest;
import org.example.contract.response.OfficeResponse;
import org.example.model.Office;
import org.mapstruct.Mapper;

@Mapper
public interface OfficeDomainMapper {
    Office requestToDomain(CreateOfficeRequest request);
    OfficeResponse domainToResponse(Office office);
    Office requestToDomain(UpdateOfficeRequest request);
}
