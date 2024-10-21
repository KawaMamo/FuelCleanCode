package org.example.mappers;

import org.example.contract.request.create.CreateBuyerRequest;
import org.example.contract.request.update.UpdateBuyerRequest;
import org.example.contract.response.BuyerResponse;
import org.example.model.Buyer;
import org.mapstruct.Mapper;

@Mapper
public interface BuyerDomainMapper {
    Buyer requestToDomain(CreateBuyerRequest request);
    Buyer requestToDomain(UpdateBuyerRequest request);
    BuyerResponse domainToResponse(Buyer buyer);
}
