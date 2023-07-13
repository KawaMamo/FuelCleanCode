package org.example.mappers;

import javax.annotation.processing.Generated;
import org.example.entities.MaterialEntity;
import org.example.model.Material;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-13T01:12:22+0300",
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

        return materialEntity;
    }

    @Override
    public Material EntityToDomain(MaterialEntity material) {
        if ( material == null ) {
            return null;
        }

        Material material1 = new Material();

        material1.setId( material.getId() );
        material1.setName( material.getName() );
        material1.setCreatedAt( material.getCreatedAt() );

        return material1;
    }
}
