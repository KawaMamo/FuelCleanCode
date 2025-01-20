package org.example.mappers;

import javax.annotation.processing.Generated;
import org.example.contract.request.create.CreateRefineryRequest;
import org.example.contract.request.update.UpdateRefineryRequest;
import org.example.contract.response.RefineryResponse;
import org.example.model.Refinery;
import org.example.model.Region;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-20T02:14:28+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
public class RefineryDomainMapperImpl implements RefineryDomainMapper {

    @Override
    public Refinery toDomain(CreateRefineryRequest request) {
        if ( request == null ) {
            return null;
        }

        Refinery refinery = new Refinery();

        refinery.setRegion( createRefineryRequestToRegion( request ) );
        refinery.setName( request.getName() );
        refinery.setTranslation( request.getTranslation() );

        return refinery;
    }

    @Override
    public RefineryResponse toResponse(Refinery refinery) {
        if ( refinery == null ) {
            return null;
        }

        RefineryResponse refineryResponse = new RefineryResponse();

        refineryResponse.setId( refinery.getId() );
        refineryResponse.setName( refinery.getName() );
        refineryResponse.setTranslation( refinery.getTranslation() );
        refineryResponse.setPlaceType( refinery.getPlaceType() );
        refineryResponse.setCreatedAt( refinery.getCreatedAt() );
        refineryResponse.setUpdatedAt( refinery.getUpdatedAt() );
        refineryResponse.setRegion( refinery.getRegion() );

        return refineryResponse;
    }

    @Override
    public Refinery toDomain(UpdateRefineryRequest request) {
        if ( request == null ) {
            return null;
        }

        Refinery refinery = new Refinery();

        refinery.setRegion( updateRefineryRequestToRegion( request ) );
        refinery.setId( request.getId() );
        refinery.setName( request.getName() );
        refinery.setTranslation( request.getTranslation() );

        return refinery;
    }

    protected Region createRefineryRequestToRegion(CreateRefineryRequest createRefineryRequest) {
        if ( createRefineryRequest == null ) {
            return null;
        }

        Region region = new Region();

        region.setId( createRefineryRequest.getRegionId() );

        return region;
    }

    protected Region updateRefineryRequestToRegion(UpdateRefineryRequest updateRefineryRequest) {
        if ( updateRefineryRequest == null ) {
            return null;
        }

        Region region = new Region();

        region.setId( updateRefineryRequest.getRegionId() );

        return region;
    }
}
