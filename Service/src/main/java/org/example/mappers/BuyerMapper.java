package org.example.mappers;

import org.example.entities.Buyer;
import org.mapstruct.Mapper;

@Mapper
public interface BuyerMapper {
    Buyer domainToEntity(org.example.model.Buyer domain);
    org.example.model.Buyer entityToDomain(Buyer entity);
}
