package org.example.mappers;

import javax.annotation.processing.Generated;
import org.example.contract.request.create.CreateGasStationRequest;
import org.example.contract.request.update.UpdateGasStationRequest;
import org.example.contract.response.GasStationResponse;
import org.example.model.GasStation;
import org.example.model.Group;
import org.example.model.Person;
import org.example.model.PriceCategory;
import org.example.model.Region;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-13T17:00:52+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
public class GasStationDomainMapperImpl implements GasStationDomainMapper {

    @Override
    public GasStation requestToDomain(CreateGasStationRequest request) {
        if ( request == null ) {
            return null;
        }

        GasStation gasStation = new GasStation();

        gasStation.setPriceCategory( createGasStationRequestToPriceCategory( request ) );
        gasStation.setRegion( createGasStationRequestToRegion( request ) );
        gasStation.setOwner( createGasStationRequestToPerson( request ) );
        gasStation.setGroup( createGasStationRequestToGroup( request ) );
        gasStation.setName( request.getName() );
        gasStation.setDebtLimit( request.getDebtLimit() );

        return gasStation;
    }

    @Override
    public GasStationResponse domainToResponse(GasStation gasStation) {
        if ( gasStation == null ) {
            return null;
        }

        GasStationResponse gasStationResponse = new GasStationResponse();

        gasStationResponse.setPlaceType( gasStation.getPlaceType() );
        gasStationResponse.setId( gasStation.getId() );
        gasStationResponse.setName( gasStation.getName() );
        gasStationResponse.setPriceCategory( gasStation.getPriceCategory() );
        gasStationResponse.setDebtLimit( gasStation.getDebtLimit() );
        gasStationResponse.setRegion( gasStation.getRegion() );
        gasStationResponse.setOwner( gasStation.getOwner() );
        gasStationResponse.setGroup( gasStation.getGroup() );
        gasStationResponse.setCreatedAt( gasStation.getCreatedAt() );
        gasStationResponse.setUpdatedAt( gasStation.getUpdatedAt() );

        return gasStationResponse;
    }

    @Override
    public GasStation requestToDomain(UpdateGasStationRequest request) {
        if ( request == null ) {
            return null;
        }

        GasStation gasStation = new GasStation();

        gasStation.setPriceCategory( updateGasStationRequestToPriceCategory( request ) );
        gasStation.setRegion( updateGasStationRequestToRegion( request ) );
        gasStation.setOwner( updateGasStationRequestToPerson( request ) );
        gasStation.setGroup( updateGasStationRequestToGroup( request ) );
        gasStation.setId( request.getId() );
        gasStation.setName( request.getName() );
        gasStation.setDebtLimit( request.getDebtLimit() );

        return gasStation;
    }

    protected PriceCategory createGasStationRequestToPriceCategory(CreateGasStationRequest createGasStationRequest) {
        if ( createGasStationRequest == null ) {
            return null;
        }

        PriceCategory priceCategory = new PriceCategory();

        priceCategory.setId( createGasStationRequest.getPriceCategoryId() );

        return priceCategory;
    }

    protected Region createGasStationRequestToRegion(CreateGasStationRequest createGasStationRequest) {
        if ( createGasStationRequest == null ) {
            return null;
        }

        Region region = new Region();

        region.setId( createGasStationRequest.getRegionId() );

        return region;
    }

    protected Person createGasStationRequestToPerson(CreateGasStationRequest createGasStationRequest) {
        if ( createGasStationRequest == null ) {
            return null;
        }

        Person person = new Person();

        person.setId( createGasStationRequest.getOwnerId() );

        return person;
    }

    protected Group createGasStationRequestToGroup(CreateGasStationRequest createGasStationRequest) {
        if ( createGasStationRequest == null ) {
            return null;
        }

        Group group = new Group();

        group.setId( createGasStationRequest.getGroupId() );

        return group;
    }

    protected PriceCategory updateGasStationRequestToPriceCategory(UpdateGasStationRequest updateGasStationRequest) {
        if ( updateGasStationRequest == null ) {
            return null;
        }

        PriceCategory priceCategory = new PriceCategory();

        priceCategory.setId( updateGasStationRequest.getPriceCategoryId() );

        return priceCategory;
    }

    protected Region updateGasStationRequestToRegion(UpdateGasStationRequest updateGasStationRequest) {
        if ( updateGasStationRequest == null ) {
            return null;
        }

        Region region = new Region();

        region.setId( updateGasStationRequest.getRegionId() );

        return region;
    }

    protected Person updateGasStationRequestToPerson(UpdateGasStationRequest updateGasStationRequest) {
        if ( updateGasStationRequest == null ) {
            return null;
        }

        Person person = new Person();

        person.setId( updateGasStationRequest.getOwnerId() );

        return person;
    }

    protected Group updateGasStationRequestToGroup(UpdateGasStationRequest updateGasStationRequest) {
        if ( updateGasStationRequest == null ) {
            return null;
        }

        Group group = new Group();

        group.setId( updateGasStationRequest.getGroupId() );

        return group;
    }
}
