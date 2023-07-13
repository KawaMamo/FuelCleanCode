package org.example.mappers;

import javax.annotation.processing.Generated;
import org.example.contract.request.CreateGasStationRequest;
import org.example.contract.response.GasStationResponse;
import org.example.model.GasStation;
import org.example.model.Group;
import org.example.model.Person;
import org.example.model.PriceCategory;
import org.example.model.Region;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-13T01:12:18+0300",
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

        gasStationResponse.setId( gasStation.getId() );
        gasStationResponse.setName( gasStation.getName() );
        gasStationResponse.setPriceCategory( gasStation.getPriceCategory() );
        gasStationResponse.setDebtLimit( gasStation.getDebtLimit() );
        gasStationResponse.setRegion( gasStation.getRegion() );
        gasStationResponse.setOwner( gasStation.getOwner() );
        gasStationResponse.setGroup( gasStation.getGroup() );
        gasStationResponse.setCreatedAt( gasStation.getCreatedAt() );

        return gasStationResponse;
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
}
