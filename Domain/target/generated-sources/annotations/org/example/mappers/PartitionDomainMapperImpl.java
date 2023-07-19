package org.example.mappers;

import javax.annotation.processing.Generated;
import org.example.contract.request.CreatePartitionRequest;
import org.example.contract.response.PartitionResponse;
import org.example.model.GasStation;
import org.example.model.Material;
import org.example.model.Partition;
import org.example.model.Transportation;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-19T22:46:24+0300",
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

        return partitionResponse;
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
}
