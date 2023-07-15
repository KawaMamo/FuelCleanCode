package org.example.mappers;

import org.example.contract.request.CreateFatTransLineRequest;
import org.example.contract.response.TransLineResponse;
import org.example.model.TransLine;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper
public interface TransLineDomainMapper {
    @Mapping(source = "feeCurrency", target = "fee.currency")
    @Mapping(source = "feeAmount", target = "fee.amount")
    TransLine requestToDomain(CreateFatTransLineRequest request);
    TransLineResponse domainToResponse(TransLine transLine);

}
