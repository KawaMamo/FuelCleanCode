package org.example.mappers;

import javax.annotation.processing.Generated;
import org.example.contract.request.create.CreateMaterialRequest;
import org.example.contract.request.update.UpdateMaterialRequest;
import org.example.contract.response.MaterialResponse;
import org.example.model.Material;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-02T23:23:06+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
public class MaterialDomainMapperImpl implements MaterialDomainMapper {

    @Override
    public Material requestToDomain(CreateMaterialRequest request) {
        if ( request == null ) {
            return null;
        }

        Material material = new Material();

        material.setName( request.getName() );

        return material;
    }

    @Override
    public MaterialResponse domainToResponse(Material material) {
        if ( material == null ) {
            return null;
        }

        MaterialResponse materialResponse = new MaterialResponse();

        materialResponse.setId( material.getId() );
        materialResponse.setName( material.getName() );
        materialResponse.setCreatedAt( material.getCreatedAt() );
        materialResponse.setUpdatedAt( material.getUpdatedAt() );

        return materialResponse;
    }

    @Override
    public Material requestToDomain(UpdateMaterialRequest request) {
        if ( request == null ) {
            return null;
        }

        Material material = new Material();

        material.setId( request.getId() );
        material.setName( request.getName() );

        return material;
    }
}
