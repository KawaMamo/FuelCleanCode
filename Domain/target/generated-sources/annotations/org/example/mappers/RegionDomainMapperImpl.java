package org.example.mappers;

import javax.annotation.processing.Generated;
import org.example.contract.request.CreateRegionRequest;
import org.example.contract.response.RegionResponse;
import org.example.model.Region;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-13T16:54:24+0300",
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
        regionResponse.setCreatedAt( region.getCreatedAt() );

        return regionResponse;
    }
}
