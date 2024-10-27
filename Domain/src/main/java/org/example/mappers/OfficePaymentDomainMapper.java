package org.example.mappers;

import org.example.contract.request.create.CreateOfficePaymentRequest;
import org.example.contract.request.update.UpdateOfficePaymentRequest;
import org.example.contract.response.OfficePaymentResponse;
import org.example.model.OfficePayment;
import org.mapstruct.Mapper;

@Mapper
public interface OfficePaymentDomainMapper {
    OfficePayment requestToDomain(CreateOfficePaymentRequest request);
    OfficePayment requestToDomain(UpdateOfficePaymentRequest request);
    OfficePaymentResponse domainToResponse(OfficePayment officePayment);
}
