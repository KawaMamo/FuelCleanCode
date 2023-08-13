package org.example.mappers;

import org.example.contract.request.create.CreateMaterialRequest;
import org.example.contract.request.update.UpdateMaterialRequest;
import org.example.contract.response.MaterialResponse;
import org.example.model.Material;
import org.mapstruct.Mapper;

@Mapper
public interface MaterialDomainMapper {
    Material requestToDomain(CreateMaterialRequest request);
    MaterialResponse domainToResponse(Material material);
    Material requestToDomain(UpdateMaterialRequest request);
}
