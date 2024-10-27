package org.example.mappers;

import org.example.entities.SellerPayment;
import org.mapstruct.Mapper;

@Mapper
public interface SellerPaymentMapper {
    SellerPayment domainToEntity(org.example.model.SellerPayment sellerPayment);
    org.example.model.SellerPayment entityToDomain(SellerPayment sellerPayment);
}
