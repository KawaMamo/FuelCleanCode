package org.example.mappers;

import org.example.contract.request.create.CreatePaidToParentRequest;
import org.example.contract.request.update.UpdatePaidToParentRequest;
import org.example.contract.response.PaidToParentResponse;
import org.example.model.PaidToParent;
import org.mapstruct.Mapper;

@Mapper
public interface PaidToParentDomainMapper {
    PaidToParent requestToDomain(CreatePaidToParentRequest request);
    PaidToParent requestToDomain(UpdatePaidToParentRequest request);
    PaidToParentResponse domainToResponse(PaidToParent domain);
}
