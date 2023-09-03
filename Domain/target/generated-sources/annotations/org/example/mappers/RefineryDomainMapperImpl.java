package org.example.mappers;

import javax.annotation.processing.Generated;
import org.example.contract.request.create.CreateRefineryRequest;
import org.example.contract.request.update.UpdateRefineryRequest;
import org.example.contract.response.RefineryResponse;
import org.example.model.Refinery;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-03T15:24:37+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 20 (Oracle Corporation)"
)
public class RefineryDomainMapperImpl implements RefineryDomainMapper {

    @Override
    public Refinery toDomain(CreateRefineryRequest request) {
        if ( request == null ) {
            return null;
        }

        Refinery refinery = new Refinery();

        refinery.setName( request.getName() );

        return refinery;
    }

    @Override
    public RefineryResponse toResponse(Refinery refinery) {
        if ( refinery == null ) {
            return null;
        }

        RefineryResponse refineryResponse = new RefineryResponse();

        refineryResponse.setPlaceType( refinery.getPlaceType() );
        refineryResponse.setCreatedAt( refinery.getCreatedAt() );
        refineryResponse.setUpdatedAt( refinery.getUpdatedAt() );
        refineryResponse.setId( refinery.getId() );
        refineryResponse.setName( refinery.getName() );

        return refineryResponse;
    }

    @Override
    public Refinery toDomain(UpdateRefineryRequest request) {
        if ( request == null ) {
            return null;
        }

        Refinery refinery = new Refinery();

        refinery.setId( request.getId() );
        refinery.setName( request.getName() );

        return refinery;
    }
}
