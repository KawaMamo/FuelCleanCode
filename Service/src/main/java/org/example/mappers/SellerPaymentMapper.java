package org.example.mappers;

import org.example.entities.SellerPayment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface SellerPaymentMapper {
    @Mapping(source = "amount.amount", target = "priceAmount")
    @Mapping(source = "amount.currency", target = "priceCurrency")
    SellerPayment domainToEntity(org.example.model.SellerPayment sellerPayment);
    @Mapping(target = "amount.amount", source = "priceAmount")
    @Mapping(target = "amount.currency", source = "priceCurrency")
    org.example.model.SellerPayment entityToDomain(SellerPayment sellerPayment);
}
