package org.example.mappers;

import javax.annotation.processing.Generated;
import org.example.contract.request.CreateRefineryRequest;
import org.example.contract.response.CreateRefineryResponse;
import org.example.model.Refinery;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-15T22:39:34+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
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
    public CreateRefineryResponse toResponse(Refinery refinery) {
        if ( refinery == null ) {
            return null;
        }

        CreateRefineryResponse createRefineryResponse = new CreateRefineryResponse();

        createRefineryResponse.setPlaceType( refinery.getPlaceType() );
        createRefineryResponse.setId( refinery.getId() );
        createRefineryResponse.setName( refinery.getName() );
        createRefineryResponse.setCreatedAt( refinery.getCreatedAt() );

        return createRefineryResponse;
    }
}
