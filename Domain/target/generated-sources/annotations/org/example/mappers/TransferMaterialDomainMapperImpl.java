package org.example.mappers;

import javax.annotation.processing.Generated;
import org.example.contract.request.CreateTransferMaterialRequest;
import org.example.contract.response.TransferMaterialResponse;
import org.example.model.GasStation;
import org.example.model.Material;
import org.example.model.TransferMaterials;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-19T23:30:07+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
public class TransferMaterialDomainMapperImpl implements TransferMaterialDomainMapper {

    @Override
    public TransferMaterials requestToDomain(CreateTransferMaterialRequest request) {
        if ( request == null ) {
            return null;
        }

        TransferMaterials transferMaterials = new TransferMaterials();

        transferMaterials.setSource( createTransferMaterialRequestToGasStation( request ) );
        transferMaterials.setDestination( createTransferMaterialRequestToGasStation1( request ) );
        transferMaterials.setMaterial( createTransferMaterialRequestToMaterial( request ) );
        transferMaterials.setAmount( request.getAmount() );
        transferMaterials.setPrice( request.getPrice() );

        return transferMaterials;
    }

    @Override
    public TransferMaterialResponse domainToResponse(TransferMaterials transferMaterials) {
        if ( transferMaterials == null ) {
            return null;
        }

        TransferMaterialResponse transferMaterialResponse = new TransferMaterialResponse();

        transferMaterialResponse.setId( transferMaterials.getId() );
        transferMaterialResponse.setSource( transferMaterials.getSource() );
        transferMaterialResponse.setDestination( transferMaterials.getDestination() );
        transferMaterialResponse.setMaterial( transferMaterials.getMaterial() );
        transferMaterialResponse.setAmount( transferMaterials.getAmount() );
        transferMaterialResponse.setPrice( transferMaterials.getPrice() );
        transferMaterialResponse.setCreatedAt( transferMaterials.getCreatedAt() );

        return transferMaterialResponse;
    }

    protected GasStation createTransferMaterialRequestToGasStation(CreateTransferMaterialRequest createTransferMaterialRequest) {
        if ( createTransferMaterialRequest == null ) {
            return null;
        }

        GasStation gasStation = new GasStation();

        gasStation.setId( createTransferMaterialRequest.getSourceId() );

        return gasStation;
    }

    protected GasStation createTransferMaterialRequestToGasStation1(CreateTransferMaterialRequest createTransferMaterialRequest) {
        if ( createTransferMaterialRequest == null ) {
            return null;
        }

        GasStation gasStation = new GasStation();

        gasStation.setId( createTransferMaterialRequest.getDestinationId() );

        return gasStation;
    }

    protected Material createTransferMaterialRequestToMaterial(CreateTransferMaterialRequest createTransferMaterialRequest) {
        if ( createTransferMaterialRequest == null ) {
            return null;
        }

        Material material = new Material();

        material.setId( createTransferMaterialRequest.getMaterialId() );

        return material;
    }
}
