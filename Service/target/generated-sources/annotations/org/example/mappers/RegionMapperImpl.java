package org.example.mappers;

import javax.annotation.processing.Generated;
import org.example.entities.RegionEntity;
import org.example.model.Region;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-19T23:30:11+0300",
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
        regionEntity.setName( region.getName() );
        regionEntity.setCreatedAt( region.getCreatedAt() );

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
        region1.setCreatedAt( region.getCreatedAt() );

        return region1;
    }
}
