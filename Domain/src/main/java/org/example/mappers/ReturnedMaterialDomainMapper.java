package org.example.mappers;

import org.example.contract.request.create.CreateReturnedMaterialRequest;
import org.example.contract.request.update.UpdateReturnedMaterialRequest;
import org.example.contract.response.ReturnedMaterialResponse;
import org.example.model.ReturnedMaterial;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ReturnedMaterialDomainMapper {
    @Mapping(source = "gasStationId", target = "gasStation.id")
    @Mapping(source = "materialId", target = "material.id")
    @Mapping(source = "buyerId", target = "buyer.id")
    ReturnedMaterial toDomain(CreateReturnedMaterialRequest request);

    ReturnedMaterialResponse toResponse(ReturnedMaterial returnedMaterial);

    @Mapping(source = "gasStationId", target = "gasStation.id")
    @Mapping(source = "materialId", target = "material.id")
    @Mapping(source = "buyerId", target = "buyer.id")
    ReturnedMaterial toDomain(UpdateReturnedMaterialRequest request);
}
