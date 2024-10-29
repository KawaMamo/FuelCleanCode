package org.example.mappers;

import org.example.entities.OfficePayment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface OfficePaymentMapper {
    @Mapping(source = "amount.amount", target = "priceAmount")
    @Mapping(source = "amount.currency", target = "priceCurrency")
    OfficePayment domainToEntity(org.example.model.OfficePayment officePayment);
    @Mapping(target = "amount.amount", source = "priceAmount")
    @Mapping(target = "amount.currency", source = "priceCurrency")
    org.example.model.OfficePayment entityToDomain(OfficePayment officePayment);
}
