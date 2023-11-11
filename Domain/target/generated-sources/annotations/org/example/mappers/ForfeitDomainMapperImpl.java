package org.example.mappers;

import javax.annotation.processing.Generated;
import org.example.contract.request.create.CreateForfeitRequest;
import org.example.contract.request.update.UpdateForfeitRequest;
import org.example.contract.response.ForfeitResponse;
import org.example.model.Forfeit;
import org.example.model.Partition;
import org.example.model.Vehicle;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-11T13:46:16+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
public class ForfeitDomainMapperImpl implements ForfeitDomainMapper {

    @Override
    public Forfeit requestToDomain(CreateForfeitRequest request) {
        if ( request == null ) {
            return null;
        }

        Forfeit forfeit = new Forfeit();

        forfeit.setVehicles( createForfeitRequestToVehicle( request ) );
        forfeit.setPartition( createForfeitRequestToPartition( request ) );
        forfeit.setPrice( request.getPrice() );
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
        forfeitResponse.setUpdatedAt( forfeit.getUpdatedAt() );

        return forfeitResponse;
    }

    @Override
    public Forfeit requestToDomain(UpdateForfeitRequest request) {
        if ( request == null ) {
            return null;
        }

        Forfeit forfeit = new Forfeit();

        forfeit.setVehicles( updateForfeitRequestToVehicle( request ) );
        forfeit.setPartition( updateForfeitRequestToPartition( request ) );
        forfeit.setId( request.getId() );
        forfeit.setPrice( request.getPrice() );
        forfeit.setReason( request.getReason() );

        return forfeit;
    }

    protected Vehicle createForfeitRequestToVehicle(CreateForfeitRequest createForfeitRequest) {
        if ( createForfeitRequest == null ) {
            return null;
        }

        Vehicle vehicle = new Vehicle();

        vehicle.setId( createForfeitRequest.getVehiclesId() );

        return vehicle;
    }

    protected Partition createForfeitRequestToPartition(CreateForfeitRequest createForfeitRequest) {
        if ( createForfeitRequest == null ) {
            return null;
        }

        Partition partition = new Partition();

        partition.setId( createForfeitRequest.getPartitionId() );

        return partition;
    }

    protected Vehicle updateForfeitRequestToVehicle(UpdateForfeitRequest updateForfeitRequest) {
        if ( updateForfeitRequest == null ) {
            return null;
        }

        Vehicle vehicle = new Vehicle();

        vehicle.setId( updateForfeitRequest.getVehiclesId() );

        return vehicle;
    }

    protected Partition updateForfeitRequestToPartition(UpdateForfeitRequest updateForfeitRequest) {
        if ( updateForfeitRequest == null ) {
            return null;
        }

        Partition partition = new Partition();

        partition.setId( updateForfeitRequest.getPartitionId() );

        return partition;
    }
}
