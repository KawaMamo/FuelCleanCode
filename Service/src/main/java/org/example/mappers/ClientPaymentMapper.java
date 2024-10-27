package org.example.mappers;

import org.example.entities.ClientPayment;
import org.mapstruct.Mapper;

@Mapper
public interface ClientPaymentMapper {
    ClientPayment domainToEntity(org.example.model.ClientPayment clientPayment);
    org.example.model.ClientPayment entityToDomain(ClientPayment clientPayment);
}
