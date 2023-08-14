package org.example.mappers;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.example.contract.request.create.CreateTransRequest;
import org.example.contract.request.update.UpdateTransRequest;
import org.example.contract.response.CreateTransResponse;
import org.example.model.Partition;
import org.example.model.Refinery;
import org.example.model.Transportation;
import org.example.model.Vehicle;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-14T14:36:24+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
public class DomainTransMapperImpl implements DomainTransMapper {

    @Override
    public Transportation toDomain(CreateTransRequest createTransRequest) {
        if ( createTransRequest == null ) {
            return null;
        }

        Transportation transportation = new Transportation();

        transportation.setRefinery( createTransRequestToRefinery( createTransRequest ) );
        transportation.setVehicle( createTransRequestToVehicle( createTransRequest ) );
        transportation.setSize( createTransRequest.getSize() );
        transportation.setType( createTransRequest.getType() );

        return transportation;
    }

    @Override
    public CreateTransResponse toResponse(Transportation transportation) {
        if ( transportation == null ) {
            return null;
        }

        CreateTransResponse createTransResponse = new CreateTransResponse();

        createTransResponse.setId( transportation.getId() );
        createTransResponse.setVehicle( transportation.getVehicle() );
        createTransResponse.setRefinery( transportation.getRefinery() );
        createTransResponse.setIsDivided( transportation.getIsDivided() );
        createTransResponse.setIsPriced( transportation.getIsPriced() );
        createTransResponse.setSize( transportation.getSize() );
        createTransResponse.setCreatedAt( transportation.getCreatedAt() );
        createTransResponse.setUpdatedAt( transportation.getUpdatedAt() );
        List<Partition> list = transportation.getPartitions();
        if ( list != null ) {
            createTransResponse.setPartitions( new ArrayList<Partition>( list ) );
        }
        createTransResponse.setType( transportation.getType() );
        createTransResponse.setDocument( transportation.getDocument() );
        createTransResponse.setDeletedAt( transportation.getDeletedAt() );

        return createTransResponse;
    }

    @Override
    public Transportation toDomain(UpdateTransRequest request) {
        if ( request == null ) {
            return null;
        }

        Transportation transportation = new Transportation();

        transportation.setRefinery( updateTransRequestToRefinery( request ) );
        transportation.setVehicle( updateTransRequestToVehicle( request ) );
        transportation.setId( request.getId() );
        transportation.setSize( request.getSize() );
        transportation.setType( request.getType() );

        return transportation;
    }

    protected Refinery createTransRequestToRefinery(CreateTransRequest createTransRequest) {
        if ( createTransRequest == null ) {
            return null;
        }

        Refinery refinery = new Refinery();

        refinery.setId( createTransRequest.getRefinery_id() );

        return refinery;
    }

    protected Vehicle createTransRequestToVehicle(CreateTransRequest createTransRequest) {
        if ( createTransRequest == null ) {
            return null;
        }

        Vehicle vehicle = new Vehicle();

        vehicle.setId( createTransRequest.getVehicle_id() );

        return vehicle;
    }

    protected Refinery updateTransRequestToRefinery(UpdateTransRequest updateTransRequest) {
        if ( updateTransRequest == null ) {
            return null;
        }

        Refinery refinery = new Refinery();

        refinery.setId( updateTransRequest.getRefinery_id() );

        return refinery;
    }

    protected Vehicle updateTransRequestToVehicle(UpdateTransRequest updateTransRequest) {
        if ( updateTransRequest == null ) {
            return null;
        }

        Vehicle vehicle = new Vehicle();

        vehicle.setId( updateTransRequest.getVehicle_id() );

        return vehicle;
    }
}
