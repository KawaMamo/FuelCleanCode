package org.example.mappers;

import org.example.contract.request.create.CreateSellerPaymentRequest;
import org.example.contract.request.update.UpdateSellerPaymentRequest;
import org.example.contract.response.SellerPaymentResponse;
import org.example.model.SellerPayment;
import org.mapstruct.Mapper;

@Mapper
public interface SellerPaymentDomainMapper {
    SellerPayment requestToDomain(CreateSellerPaymentRequest request);
    SellerPayment requestToDomain(UpdateSellerPaymentRequest request);
    SellerPaymentResponse domainToResponse(SellerPayment domain);
}
