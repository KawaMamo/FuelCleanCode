package org.example.mappers;

import org.example.entities.PaidToParent;
import org.mapstruct.Mapper;

@Mapper
public interface PaidToParentMapper {
    PaidToParent domainToEntity(org.example.model.PaidToParent domain);
    org.example.model.PaidToParent entityToDomain(PaidToParent entity);
}
