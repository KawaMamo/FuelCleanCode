package org.example.mappers;

import javax.annotation.processing.Generated;
import org.example.model.BuyOperation;
import org.example.model.Document;
import org.example.model.Office;
import org.example.model.Person;
import org.example.model.Refinery;
import org.example.model.TrafficCenter;
import org.example.model.Vehicle;
import org.example.repositories.entities.OfficeEntity;
import org.example.repositories.entities.PersonEntity;
import org.example.repositories.entities.RefineryEntity;
import org.example.repositories.entities.TrafficCenterEntity;
import org.example.repositories.entities.TransportationType;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-06T01:19:09+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
public class VehicleMapperImpl implements VehicleMapper {

    @Override
    public org.example.repositories.entities.Vehicle domainToEntity(Vehicle vehicle) {
        if ( vehicle == null ) {
            return null;
        }

        org.example.repositories.entities.Vehicle vehicle1 = new org.example.repositories.entities.Vehicle();

        vehicle1.setId( vehicle.getId() );
        vehicle1.setPlateNumber( vehicle.getPlateNumber() );
        vehicle1.setTrafficCenter( domainToEntity( vehicle.getTrafficCenter() ) );
        vehicle1.setSize( vehicle.getSize() );
        vehicle1.setOffice( domainToEntity( vehicle.getOffice() ) );
        vehicle1.setDriver( domainToEntity( vehicle.getDriver() ) );
        vehicle1.setCreatedAt( vehicle.getCreatedAt() );

        return vehicle1;
    }

    @Override
    public PersonEntity domainToEntity(Person person) {
        if ( person == null ) {
            return null;
        }

        PersonEntity personEntity = new PersonEntity();

        return personEntity;
    }

    @Override
    public org.example.repositories.entities.BuyOperation domainToEntity(BuyOperation buyOperation) {
        if ( buyOperation == null ) {
            return null;
        }

        org.example.repositories.entities.BuyOperation buyOperation1 = new org.example.repositories.entities.BuyOperation();

        buyOperation1.setId( buyOperation.getId() );
        buyOperation1.setVehicle( domainToEntity( buyOperation.getVehicle() ) );
        buyOperation1.setRefinery( refineryToRefineryEntity( buyOperation.getRefinery() ) );
        buyOperation1.setIsDivided( buyOperation.getIsDivided() );
        buyOperation1.setIsPriced( buyOperation.getIsPriced() );
        buyOperation1.setSize( buyOperation.getSize() );
        buyOperation1.setCreatedAt( buyOperation.getCreatedAt() );
        buyOperation1.setType( transportationTypeToTransportationType( buyOperation.getType() ) );
        buyOperation1.setDocument( documentToDocument( buyOperation.getDocument() ) );
        buyOperation1.setDeletedAt( buyOperation.getDeletedAt() );
        buyOperation1.setSource( buyOperation.getSource() );

        return buyOperation1;
    }

    @Override
    public Vehicle EntityToDomain(org.example.repositories.entities.Vehicle save) {
        if ( save == null ) {
            return null;
        }

        Vehicle vehicle = new Vehicle();

        vehicle.setId( save.getId() );
        vehicle.setPlateNumber( save.getPlateNumber() );
        vehicle.setTrafficCenter( trafficCenterEntityToTrafficCenter( save.getTrafficCenter() ) );
        vehicle.setSize( save.getSize() );
        vehicle.setOffice( officeEntityToOffice( save.getOffice() ) );
        vehicle.setDriver( personEntityToPerson( save.getDriver() ) );
        vehicle.setCreatedAt( save.getCreatedAt() );

        return vehicle;
    }

    @Override
    public OfficeEntity domainToEntity(Office office) {
        if ( office == null ) {
            return null;
        }

        OfficeEntity officeEntity = new OfficeEntity();

        officeEntity.setId( office.getId() );
        officeEntity.setName( office.getName() );
        officeEntity.setCreatedAt( office.getCreatedAt() );

        return officeEntity;
    }

    @Override
    public TrafficCenterEntity domainToEntity(TrafficCenter trafficCenter) {
        if ( trafficCenter == null ) {
            return null;
        }

        TrafficCenterEntity trafficCenterEntity = new TrafficCenterEntity();

        return trafficCenterEntity;
    }

    protected RefineryEntity refineryToRefineryEntity(Refinery refinery) {
        if ( refinery == null ) {
            return null;
        }

        RefineryEntity refineryEntity = new RefineryEntity();

        refineryEntity.setId( refinery.getId() );
        refineryEntity.setName( refinery.getName() );
        refineryEntity.setCreatedAt( refinery.getCreatedAt() );

        return refineryEntity;
    }

    protected TransportationType transportationTypeToTransportationType(org.example.model.TransportationType transportationType) {
        if ( transportationType == null ) {
            return null;
        }

        TransportationType transportationType1;

        switch ( transportationType ) {
            case NORMAL: transportationType1 = TransportationType.NORMAL;
            break;
            case COMMERCIAL: transportationType1 = TransportationType.COMMERCIAL;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + transportationType );
        }

        return transportationType1;
    }

    protected org.example.repositories.entities.Document documentToDocument(Document document) {
        if ( document == null ) {
            return null;
        }

        org.example.repositories.entities.Document document1 = new org.example.repositories.entities.Document();

        document1.setId( document.getId() );
        document1.setUrl( document.getUrl() );
        document1.setType( document.getType() );
        document1.setResourceId( document.getResourceId() );
        document1.setCreatedAt( document.getCreatedAt() );

        return document1;
    }

    protected TrafficCenter trafficCenterEntityToTrafficCenter(TrafficCenterEntity trafficCenterEntity) {
        if ( trafficCenterEntity == null ) {
            return null;
        }

        TrafficCenter trafficCenter = new TrafficCenter();

        return trafficCenter;
    }

    protected Office officeEntityToOffice(OfficeEntity officeEntity) {
        if ( officeEntity == null ) {
            return null;
        }

        Office office = new Office();

        office.setId( officeEntity.getId() );
        office.setName( officeEntity.getName() );
        office.setCreatedAt( officeEntity.getCreatedAt() );

        return office;
    }

    protected Person personEntityToPerson(PersonEntity personEntity) {
        if ( personEntity == null ) {
            return null;
        }

        Person person = new Person();

        return person;
    }
}
