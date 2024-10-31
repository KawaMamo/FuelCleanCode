package org.example.mappers;

import org.example.entities.Seller;
import org.mapstruct.Mapper;

@Mapper
public interface SellerMapper {
    Seller domainToEntity(org.example.model.Seller seller);
    org.example.model.Seller entityToDomain(Seller seller);
}
