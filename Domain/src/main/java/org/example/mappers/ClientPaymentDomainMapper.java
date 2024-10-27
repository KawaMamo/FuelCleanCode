package org.example.mappers;

import org.example.contract.request.create.CreateClientPaymentRequest;
import org.example.contract.request.update.UpdateClientPaymentRequest;
import org.example.contract.response.ClientPaymentResponse;
import org.example.model.ClientPayment;
import org.mapstruct.Mapper;

@Mapper
public interface ClientPaymentDomainMapper {
    ClientPayment requestToDomain(CreateClientPaymentRequest request);
    ClientPayment requestToDomain(UpdateClientPaymentRequest request);
    ClientPaymentResponse domainToResponse(ClientPayment domain);
}
