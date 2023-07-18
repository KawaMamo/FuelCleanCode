package org.example.mappers;

import org.example.contract.request.CreateTransferMaterialRequest;
import org.example.contract.response.TransferMaterialResponse;
import org.example.model.TransferMaterials;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface TransferMaterialDomainMapper {
    @Mapping(source = "sourceId", target = "source.id")
    @Mapping(source = "destinationId", target = "destination.id")
    @Mapping(source = "materialId", target = "material.id")
    TransferMaterials requestToDomain(CreateTransferMaterialRequest request);
    TransferMaterialResponse domainToResponse(TransferMaterials transferMaterials);
}
