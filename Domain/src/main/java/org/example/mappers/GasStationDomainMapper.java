package org.example.mappers;

import org.example.contract.request.create.CreateGasStationRequest;
import org.example.contract.request.update.UpdateGasStationRequest;
import org.example.contract.response.GasStationResponse;
import org.example.model.GasStation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper
public interface GasStationDomainMapper {
    @Mapping(source = "priceCategoryId", target = "priceCategory.id")
    @Mapping(source = "regionId", target = "region.id")
    @Mapping(source = "ownerId", target = "owner.id")
    @Mapping(source = "groupId", target = "group.id")
    @Mapping(source = "materialId", target = "material.id")
    GasStation requestToDomain(CreateGasStationRequest request);

    GasStationResponse domainToResponse(GasStation gasStation);
    @Mapping(source = "priceCategoryId", target = "priceCategory.id")
    @Mapping(source = "regionId", target = "region.id")
    @Mapping(source = "ownerId", target = "owner.id")
    @Mapping(source = "groupId", target = "group.id")
    @Mapping(source = "materialId", target = "material.id")
    GasStation requestToDomain(UpdateGasStationRequest request);
}
