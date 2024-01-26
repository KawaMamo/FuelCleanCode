package org.example.mappers;

import javax.annotation.processing.Generated;
import org.example.contract.request.create.CreatePartitionRequest;
import org.example.contract.request.update.UpdatePartitionRequest;
import org.example.contract.response.PartitionResponse;
import org.example.model.GasStation;
import org.example.model.Material;
import org.example.model.Partition;
import org.example.model.Transportation;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-26T16:17:41+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 20 (Oracle Corporation)"
)
public class PartitionDomainMapperImpl implements PartitionDomainMapper {

    @Override
    public Partition requestToDomain(CreatePartitionRequest request) {
        if ( request == null ) {
            return null;
        }

        Partition partition = new Partition();

        partition.setMaterial( createPartitionRequestToMaterial( request ) );
        partition.setGasStation( createPartitionRequestToGasStation( request ) );
        partition.setTransportation( createPartitionRequestToTransportation( request ) );
        partition.setAmount( request.getAmount() );
        partition.setCorrectedAmount( request.getCorrectedAmount() );
        partition.setPrice( request.getPrice() );
        partition.setNotes( request.getNotes() );
        partition.setExtraNotes( request.getExtraNotes() );

        return partition;
    }

    @Override
    public PartitionResponse domainToResponse(Partition partition) {
        if ( partition == null ) {
            return null;
        }

        PartitionResponse partitionResponse = new PartitionResponse();

        partitionResponse.setId( partition.getId() );
        partitionResponse.setMaterial( partition.getMaterial() );
        partitionResponse.setAmount( partition.getAmount() );
        partitionResponse.setCorrectedAmount( partition.getCorrectedAmount() );
        partitionResponse.setPrice( partition.getPrice() );
        partitionResponse.setGasStation( partition.getGasStation() );
        partitionResponse.setNotes( partition.getNotes() );
        partitionResponse.setExtraNotes( partition.getExtraNotes() );
        partitionResponse.setDocument( partition.getDocument() );
        partitionResponse.setTransportation( partition.getTransportation() );
        partitionResponse.setCreatedAt( partition.getCreatedAt() );
        partitionResponse.setUpdatedAt( partition.getUpdatedAt() );

        return partitionResponse;
    }

    @Override
    public Partition requestToDomain(UpdatePartitionRequest request) {
        if ( request == null ) {
            return null;
        }

        Partition partition = new Partition();

        partition.setMaterial( updatePartitionRequestToMaterial( request ) );
        partition.setGasStation( updatePartitionRequestToGasStation( request ) );
        partition.setTransportation( updatePartitionRequestToTransportation( request ) );
        partition.setId( request.getId() );
        partition.setAmount( request.getAmount() );
        partition.setCorrectedAmount( request.getCorrectedAmount() );
        partition.setPrice( request.getPrice() );
        partition.setNotes( request.getNotes() );
        partition.setExtraNotes( request.getExtraNotes() );

        return partition;
    }

    protected Material createPartitionRequestToMaterial(CreatePartitionRequest createPartitionRequest) {
        if ( createPartitionRequest == null ) {
            return null;
        }

        Material material = new Material();

        material.setId( createPartitionRequest.getMaterialId() );

        return material;
    }

    protected GasStation createPartitionRequestToGasStation(CreatePartitionRequest createPartitionRequest) {
        if ( createPartitionRequest == null ) {
            return null;
        }

        GasStation gasStation = new GasStation();

        gasStation.setId( createPartitionRequest.getGasStationId() );

        return gasStation;
    }

    protected Transportation createPartitionRequestToTransportation(CreatePartitionRequest createPartitionRequest) {
        if ( createPartitionRequest == null ) {
            return null;
        }

        Transportation transportation = new Transportation();

        transportation.setId( createPartitionRequest.getTransportationId() );

        return transportation;
    }

    protected Material updatePartitionRequestToMaterial(UpdatePartitionRequest updatePartitionRequest) {
        if ( updatePartitionRequest == null ) {
            return null;
        }

        Material material = new Material();

        material.setId( updatePartitionRequest.getMaterialId() );

        return material;
    }

    protected GasStation updatePartitionRequestToGasStation(UpdatePartitionRequest updatePartitionRequest) {
        if ( updatePartitionRequest == null ) {
            return null;
        }

        GasStation gasStation = new GasStation();

        gasStation.setId( updatePartitionRequest.getGasStationId() );

        return gasStation;
    }

    protected Transportation updatePartitionRequestToTransportation(UpdatePartitionRequest updatePartitionRequest) {
        if ( updatePartitionRequest == null ) {
            return null;
        }

        Transportation transportation = new Transportation();

        transportation.setId( updatePartitionRequest.getTransportationId() );

        return transportation;
    }
}
