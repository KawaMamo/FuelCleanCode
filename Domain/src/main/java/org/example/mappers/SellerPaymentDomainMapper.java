package org.example.mappers;

import org.example.contract.request.create.CreateSellerPaymentRequest;
import org.example.contract.request.update.UpdateSellerPaymentRequest;
import org.example.contract.response.SellerPaymentResponse;
import org.example.model.SellerPayment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface SellerPaymentDomainMapper {
    @Mapping(source = "sellerId", target = "seller.id")
    SellerPayment requestToDomain(CreateSellerPaymentRequest request);
    @Mapping(source = "sellerId", target = "seller.id")
    SellerPayment requestToDomain(UpdateSellerPaymentRequest request);
    SellerPaymentResponse domainToResponse(SellerPayment domain);
}
