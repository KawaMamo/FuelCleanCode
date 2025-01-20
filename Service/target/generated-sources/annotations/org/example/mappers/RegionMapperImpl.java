package org.example.mappers;

import javax.annotation.processing.Generated;
import org.example.entities.RegionEntity;
import org.example.model.Region;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-20T02:14:33+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
public class RegionMapperImpl implements RegionMapper {

    @Override
    public RegionEntity domainToEntity(Region region) {
        if ( region == null ) {
            return null;
        }

        RegionEntity regionEntity = new RegionEntity();

        regionEntity.setId( region.getId() );
        regionEntity.setPlaceType( region.getPlaceType() );
        regionEntity.setCreatedAt( region.getCreatedAt() );
        regionEntity.setUpdatedAt( region.getUpdatedAt() );
        regionEntity.setName( region.getName() );
        regionEntity.setTranslation( region.getTranslation() );

        return regionEntity;
    }

    @Override
    public Region entityToDomain(RegionEntity region) {
        if ( region == null ) {
            return null;
        }

        Region region1 = new Region();

        region1.setId( region.getId() );
        region1.setName( region.getName() );
        region1.setTranslation( region.getTranslation() );
        region1.setPlaceType( region.getPlaceType() );
        region1.setCreatedAt( region.getCreatedAt() );
        region1.setUpdatedAt( region.getUpdatedAt() );

        return region1;
    }
}
