package org.example.mappers;

import org.example.contract.request.create.CreateClientPaymentRequest;
import org.example.contract.request.update.UpdateClientPaymentRequest;
import org.example.contract.response.ClientPaymentResponse;
import org.example.model.ClientPayment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ClientPaymentDomainMapper {
    @Mapping(source = "gasStationId", target ="gasStation.id")
    ClientPayment requestToDomain(CreateClientPaymentRequest request);
    @Mapping(source = "gasStationId", target ="gasStation.id")
    ClientPayment requestToDomain(UpdateClientPaymentRequest request);
    ClientPaymentResponse domainToResponse(ClientPayment domain);
}
