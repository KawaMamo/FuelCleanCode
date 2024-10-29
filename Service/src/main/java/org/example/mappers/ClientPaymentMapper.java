package org.example.mappers;

import org.example.entities.ClientPayment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ClientPaymentMapper {
    @Mapping(source = "amount.amount", target = "priceAmount")
    @Mapping(source = "amount.currency", target = "priceCurrency")
    ClientPayment domainToEntity(org.example.model.ClientPayment clientPayment);
    @Mapping(target = "amount.amount", source = "priceAmount")
    @Mapping(target = "amount.currency", source = "priceCurrency")
    org.example.model.ClientPayment entityToDomain(ClientPayment clientPayment);
}
