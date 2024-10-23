package org.example.mappers;

import javax.annotation.processing.Generated;
import org.example.contract.request.create.CreateReturnedMaterialRequest;
import org.example.contract.request.update.UpdateReturnedMaterialRequest;
import org.example.contract.response.ReturnedMaterialResponse;
import org.example.model.Buyer;
import org.example.model.GasStation;
import org.example.model.Material;
import org.example.model.ReturnedMaterial;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-23T18:27:59+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
public class ReturnedMaterialDomainMapperImpl implements ReturnedMaterialDomainMapper {

    @Override
    public ReturnedMaterial toDomain(CreateReturnedMaterialRequest request) {
        if ( request == null ) {
            return null;
        }

        ReturnedMaterial returnedMaterial = new ReturnedMaterial();

        returnedMaterial.setGasStation( createReturnedMaterialRequestToGasStation( request ) );
        returnedMaterial.setMaterial( createReturnedMaterialRequestToMaterial( request ) );
        returnedMaterial.setBuyer( createReturnedMaterialRequestToBuyer( request ) );
        returnedMaterial.setAmount( request.getAmount() );
        returnedMaterial.setPrice( request.getPrice() );
        returnedMaterial.setCenterPrice( request.getCenterPrice() );
        returnedMaterial.setStatus( request.getStatus() );
        returnedMaterial.setInvoiceNo( request.getInvoiceNo() );

        return returnedMaterial;
    }

    @Override
    public ReturnedMaterialResponse toResponse(ReturnedMaterial returnedMaterial) {
        if ( returnedMaterial == null ) {
            return null;
        }

        ReturnedMaterialResponse returnedMaterialResponse = new ReturnedMaterialResponse();

        returnedMaterialResponse.setId( returnedMaterial.getId() );
        returnedMaterialResponse.setGasStation( returnedMaterial.getGasStation() );
        returnedMaterialResponse.setMaterial( returnedMaterial.getMaterial() );
        returnedMaterialResponse.setAmount( returnedMaterial.getAmount() );
        returnedMaterialResponse.setPrice( returnedMaterial.getPrice() );
        returnedMaterialResponse.setCenterPrice( returnedMaterial.getCenterPrice() );
        returnedMaterialResponse.setStatus( returnedMaterial.getStatus() );
        returnedMaterialResponse.setBuyer( returnedMaterial.getBuyer() );
        returnedMaterialResponse.setInvoiceNo( returnedMaterial.getInvoiceNo() );
        returnedMaterialResponse.setCreatedAt( returnedMaterial.getCreatedAt() );
        returnedMaterialResponse.setUpdatedAt( returnedMaterial.getUpdatedAt() );

        return returnedMaterialResponse;
    }

    @Override
    public ReturnedMaterial toDomain(UpdateReturnedMaterialRequest request) {
        if ( request == null ) {
            return null;
        }

        ReturnedMaterial returnedMaterial = new ReturnedMaterial();

        returnedMaterial.setGasStation( updateReturnedMaterialRequestToGasStation( request ) );
        returnedMaterial.setMaterial( updateReturnedMaterialRequestToMaterial( request ) );
        returnedMaterial.setBuyer( updateReturnedMaterialRequestToBuyer( request ) );
        returnedMaterial.setId( request.getId() );
        returnedMaterial.setAmount( request.getAmount() );
        returnedMaterial.setPrice( request.getPrice() );
        returnedMaterial.setCenterPrice( request.getCenterPrice() );
        returnedMaterial.setStatus( request.getStatus() );
        returnedMaterial.setInvoiceNo( request.getInvoiceNo() );

        return returnedMaterial;
    }

    protected GasStation createReturnedMaterialRequestToGasStation(CreateReturnedMaterialRequest createReturnedMaterialRequest) {
        if ( createReturnedMaterialRequest == null ) {
            return null;
        }

        GasStation gasStation = new GasStation();

        gasStation.setId( createReturnedMaterialRequest.getGasStationId() );

        return gasStation;
    }

    protected Material createReturnedMaterialRequestToMaterial(CreateReturnedMaterialRequest createReturnedMaterialRequest) {
        if ( createReturnedMaterialRequest == null ) {
            return null;
        }

        Material material = new Material();

        material.setId( createReturnedMaterialRequest.getMaterialId() );

        return material;
    }

    protected Buyer createReturnedMaterialRequestToBuyer(CreateReturnedMaterialRequest createReturnedMaterialRequest) {
        if ( createReturnedMaterialRequest == null ) {
            return null;
        }

        Buyer buyer = new Buyer();

        buyer.setId( createReturnedMaterialRequest.getBuyerId() );

        return buyer;
    }

    protected GasStation updateReturnedMaterialRequestToGasStation(UpdateReturnedMaterialRequest updateReturnedMaterialRequest) {
        if ( updateReturnedMaterialRequest == null ) {
            return null;
        }

        GasStation gasStation = new GasStation();

        gasStation.setId( updateReturnedMaterialRequest.getGasStationId() );

        return gasStation;
    }

    protected Material updateReturnedMaterialRequestToMaterial(UpdateReturnedMaterialRequest updateReturnedMaterialRequest) {
        if ( updateReturnedMaterialRequest == null ) {
            return null;
        }

        Material material = new Material();

        material.setId( updateReturnedMaterialRequest.getMaterialId() );

        return material;
    }

    protected Buyer updateReturnedMaterialRequestToBuyer(UpdateReturnedMaterialRequest updateReturnedMaterialRequest) {
        if ( updateReturnedMaterialRequest == null ) {
            return null;
        }

        Buyer buyer = new Buyer();

        buyer.setId( updateReturnedMaterialRequest.getBuyerId() );

        return buyer;
    }
}
