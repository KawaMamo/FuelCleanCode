package org.example.mappers;

import org.example.entities.OfficePayment;
import org.mapstruct.Mapper;

@Mapper
public interface OfficePaymentMapper {
    OfficePayment domainToEntity(org.example.model.OfficePayment officePayment);
    org.example.model.OfficePayment entityToDomain(OfficePayment officePayment);
}
