package org.example.mappers;

import org.example.contract.request.create.CreateSellerRequest;
import org.example.contract.request.update.UpdateSellerRequest;
import org.example.contract.response.SellerResponse;
import org.example.model.Seller;
import org.mapstruct.Mapper;

@Mapper
public interface SellerDomainMapper {
    Seller requestToDomain(CreateSellerRequest request);
    Seller requestToDomain(UpdateSellerRequest request);
    SellerResponse domainToResponse(Seller seller);
}
