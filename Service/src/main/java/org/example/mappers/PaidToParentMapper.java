package org.example.mappers;

import org.example.entities.PaidToParent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface PaidToParentMapper {
    @Mapping(source = "amount.amount", target = "priceAmount")
    @Mapping(source = "amount.currency", target = "priceCurrency")

    PaidToParent domainToEntity(org.example.model.PaidToParent domain);
    @Mapping(target = "amount.amount", source = "priceAmount")
    @Mapping(target = "amount.currency", source = "priceCurrency")
    org.example.model.PaidToParent entityToDomain(PaidToParent entity);
}
