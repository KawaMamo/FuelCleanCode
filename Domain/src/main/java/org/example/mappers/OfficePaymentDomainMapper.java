package org.example.mappers;

import org.example.contract.request.create.CreateOfficePaymentRequest;
import org.example.contract.request.update.UpdateOfficePaymentRequest;
import org.example.contract.response.OfficePaymentResponse;
import org.example.model.OfficePayment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface OfficePaymentDomainMapper {
    @Mapping(source = "officeId", target = "office.id")
    OfficePayment requestToDomain(CreateOfficePaymentRequest request);
    @Mapping(source = "officeId", target = "office.id")
    OfficePayment requestToDomain(UpdateOfficePaymentRequest request);
    OfficePaymentResponse domainToResponse(OfficePayment officePayment);
}
