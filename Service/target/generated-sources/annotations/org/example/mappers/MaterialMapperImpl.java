package org.example.mappers;

import javax.annotation.processing.Generated;
import org.example.entities.MaterialEntity;
import org.example.model.Material;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-20T15:31:01+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
public class MaterialMapperImpl implements MaterialMapper {

    @Override
    public MaterialEntity domainToEntity(Material material) {
        if ( material == null ) {
            return null;
        }

        MaterialEntity materialEntity = new MaterialEntity();

        materialEntity.setId( material.getId() );
        materialEntity.setName( material.getName() );
        materialEntity.setCreatedAt( material.getCreatedAt() );
        materialEntity.setUpdatedAt( material.getUpdatedAt() );

        return materialEntity;
    }

    @Override
    public Material entityToDomain(MaterialEntity material) {
        if ( material == null ) {
            return null;
        }

        Material material1 = new Material();

        material1.setId( material.getId() );
        material1.setName( material.getName() );
        material1.setCreatedAt( material.getCreatedAt() );
        material1.setUpdatedAt( material.getUpdatedAt() );

        return material1;
    }
}
