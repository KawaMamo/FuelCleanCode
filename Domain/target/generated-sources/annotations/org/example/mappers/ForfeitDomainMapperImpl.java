package org.example.mappers;

import javax.annotation.processing.Generated;
import org.example.contract.request.CreateForfeitRequest;
import org.example.contract.response.ForfeitResponse;
import org.example.model.Forfeit;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-13T16:54:24+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
public class ForfeitDomainMapperImpl implements ForfeitDomainMapper {

    @Override
    public Forfeit requestToDomain(CreateForfeitRequest request) {
        if ( request == null ) {
            return null;
        }

        Forfeit forfeit = new Forfeit();

        forfeit.setReason( request.getReason() );

        return forfeit;
    }

    @Override
    public ForfeitResponse domainToResponse(Forfeit forfeit) {
        if ( forfeit == null ) {
            return null;
        }

        ForfeitResponse forfeitResponse = new ForfeitResponse();

        forfeitResponse.setId( forfeit.getId() );
        forfeitResponse.setVehicles( forfeit.getVehicles() );
        forfeitResponse.setPartition( forfeit.getPartition() );
        forfeitResponse.setPrice( forfeit.getPrice() );
        forfeitResponse.setReason( forfeit.getReason() );
        forfeitResponse.setCreatedAt( forfeit.getCreatedAt() );

        return forfeitResponse;
    }
}
