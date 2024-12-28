package org.example.mappers;

import javax.annotation.processing.Generated;
import org.example.contract.request.create.CreateRegionRequest;
import org.example.contract.request.update.UpdateRegionRequest;
import org.example.contract.response.RegionResponse;
import org.example.model.Region;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-02T23:23:06+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
public class RegionDomainMapperImpl implements RegionDomainMapper {

    @Override
    public Region requestToDomain(CreateRegionRequest request) {
        if ( request == null ) {
            return null;
        }

        Region region = new Region();

        region.setName( request.getName() );
        region.setTranslation( request.getTranslation() );

        return region;
    }

    @Override
    public RegionResponse domainToResponse(Region region) {
        if ( region == null ) {
            return null;
        }

        RegionResponse regionResponse = new RegionResponse();

        regionResponse.setId( region.getId() );
        regionResponse.setName( region.getName() );
        regionResponse.setTranslation( region.getTranslation() );
        regionResponse.setPlaceType( region.getPlaceType() );
        regionResponse.setCreatedAt( region.getCreatedAt() );
        regionResponse.setUpdatedAt( region.getUpdatedAt() );

        return regionResponse;
    }

    @Override
    public Region requestToDomain(UpdateRegionRequest request) {
        if ( request == null ) {
            return null;
        }

        Region region = new Region();

        region.setId( request.getId() );
        region.setName( request.getName() );
        region.setTranslation( request.getTranslation() );

        return region;
    }
}
