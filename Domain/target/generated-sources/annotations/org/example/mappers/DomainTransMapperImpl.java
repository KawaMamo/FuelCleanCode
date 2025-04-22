package org.example.mappers;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.example.contract.request.create.CreateTransRequest;
import org.example.contract.request.update.UpdateTransRequest;
import org.example.contract.response.TransResponse;
import org.example.model.Document;
import org.example.model.Partition;
import org.example.model.Refinery;
import org.example.model.TransLog;
import org.example.model.Transportation;
import org.example.model.Vehicle;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-22T19:26:17+0300",
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
        transportation.setType( createTransRequest.getType() );
        transportation.setSize( createTransRequest.getSize() );

        return transportation;
    }

    @Override
    public TransResponse toResponse(Transportation transportation) {
        if ( transportation == null ) {
            return null;
        }

        TransResponse transResponse = new TransResponse();

        transResponse.setId( transportation.getId() );
        transResponse.setType( transportation.getType() );
        transResponse.setRefinery( transportation.getRefinery() );
        transResponse.setIsDivided( transportation.getIsDivided() );
        transResponse.setIsPriced( transportation.getIsPriced() );
        transResponse.setSize( transportation.getSize() );
        transResponse.setCreatedAt( transportation.getCreatedAt() );
        transResponse.setUpdatedAt( transportation.getUpdatedAt() );
        List<Partition> list = transportation.getPartitions();
        if ( list != null ) {
            transResponse.setPartitions( new ArrayList<Partition>( list ) );
        }
        List<TransLog> list1 = transportation.getTransLogs();
        if ( list1 != null ) {
            transResponse.setTransLogs( new ArrayList<TransLog>( list1 ) );
        }
        List<Document> list2 = transportation.getDocument();
        if ( list2 != null ) {
            transResponse.setDocument( new ArrayList<Document>( list2 ) );
        }
        transResponse.setDeletedAt( transportation.getDeletedAt() );
        transResponse.setVehicle( transportation.getVehicle() );

        return transResponse;
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
        transportation.setType( request.getType() );
        transportation.setSize( request.getSize() );

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
