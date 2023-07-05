package org.example.mappers;

import javax.annotation.processing.Generated;
import org.example.model.Document;
import org.example.model.Office;
import org.example.model.Person;
import org.example.model.Refinery;
import org.example.model.TrafficCenter;
import org.example.model.Transportation;
import org.example.model.Vehicle;
import org.example.repositories.entities.OfficeEntity;
import org.example.repositories.entities.PersonEntity;
import org.example.repositories.entities.RefineryEntity;
import org.example.repositories.entities.TrafficCenterEntity;
import org.example.repositories.entities.TransportationEntity;
import org.example.repositories.entities.TransportationType;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-04T17:39:57+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
public class TransMapperImpl implements TransMapper {

    @Override
    public TransportationEntity transportationEntity(Transportation transportation) {
        if ( transportation == null ) {
            return null;
        }

        TransportationEntity transportationEntity = new TransportationEntity();

        transportationEntity.setId( transportation.getId() );
        transportationEntity.setVehicle( toVehicle( transportation.getVehicle() ) );
        transportationEntity.setRefinery( toRefinery( transportation.getRefinery() ) );
        transportationEntity.setIsDivided( transportation.getIsDivided() );
        transportationEntity.setIsPriced( transportation.getIsPriced() );
        transportationEntity.setSize( transportation.getSize() );
        transportationEntity.setCreatedAt( transportation.getCreatedAt() );
        transportationEntity.setType( transportationTypeToTransportationType( transportation.getType() ) );
        transportationEntity.setDocument( documentToDocument( transportation.getDocument() ) );
        transportationEntity.setDeletedAt( transportation.getDeletedAt() );

        return transportationEntity;
    }

    @Override
    public Transportation toTransportation(TransportationEntity transportationEntity) {
        if ( transportationEntity == null ) {
            return null;
        }

        Transportation transportation = new Transportation();

        transportation.setId( transportationEntity.getId() );
        transportation.setVehicle( vehicleToVehicle( transportationEntity.getVehicle() ) );
        transportation.setRefinery( refineryEntityToRefinery( transportationEntity.getRefinery() ) );
        transportation.setIsDivided( transportationEntity.getIsDivided() );
        transportation.setIsPriced( transportationEntity.getIsPriced() );
        transportation.setSize( transportationEntity.getSize() );
        transportation.setCreatedAt( transportationEntity.getCreatedAt() );
        transportation.setType( transportationTypeToTransportationType1( transportationEntity.getType() ) );
        transportation.setDocument( documentToDocument1( transportationEntity.getDocument() ) );
        transportation.setDeletedAt( transportationEntity.getDeletedAt() );

        return transportation;
    }

    @Override
    public org.example.repositories.entities.Vehicle toVehicle(Vehicle vehicle) {
        if ( vehicle == null ) {
            return null;
        }

        org.example.repositories.entities.Vehicle vehicle1 = new org.example.repositories.entities.Vehicle();

        vehicle1.setId( vehicle.getId() );
        vehicle1.setPlateNumber( vehicle.getPlateNumber() );
        vehicle1.setTrafficCenter( toEntity( vehicle.getTrafficCenter() ) );
        vehicle1.setSize( vehicle.getSize() );
        vehicle1.setOffice( toEntity( vehicle.getOffice() ) );
        vehicle1.setDriver( toEntity( vehicle.getDriver() ) );
        vehicle1.setCreatedAt( vehicle.getCreatedAt() );

        return vehicle1;
    }

    @Override
    public RefineryEntity toRefinery(Refinery refinery) {
        if ( refinery == null ) {
            return null;
        }

        RefineryEntity refineryEntity = new RefineryEntity();

        refineryEntity.setId( refinery.getId() );
        refineryEntity.setName( refinery.getName() );
        refineryEntity.setCreatedAt( refinery.getCreatedAt() );

        return refineryEntity;
    }

    @Override
    public TrafficCenterEntity toEntity(TrafficCenter trafficCenter) {
        if ( trafficCenter == null ) {
            return null;
        }

        TrafficCenterEntity trafficCenterEntity = new TrafficCenterEntity();

        return trafficCenterEntity;
    }

    @Override
    public OfficeEntity toEntity(Office office) {
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
    public PersonEntity toEntity(Person person) {
        if ( person == null ) {
            return null;
        }

        PersonEntity personEntity = new PersonEntity();

        return personEntity;
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

    protected Vehicle vehicleToVehicle(org.example.repositories.entities.Vehicle vehicle) {
        if ( vehicle == null ) {
            return null;
        }

        Vehicle vehicle1 = new Vehicle();

        vehicle1.setId( vehicle.getId() );
        vehicle1.setPlateNumber( vehicle.getPlateNumber() );
        vehicle1.setTrafficCenter( trafficCenterEntityToTrafficCenter( vehicle.getTrafficCenter() ) );
        vehicle1.setSize( vehicle.getSize() );
        vehicle1.setOffice( officeEntityToOffice( vehicle.getOffice() ) );
        vehicle1.setDriver( personEntityToPerson( vehicle.getDriver() ) );
        vehicle1.setCreatedAt( vehicle.getCreatedAt() );

        return vehicle1;
    }

    protected Refinery refineryEntityToRefinery(RefineryEntity refineryEntity) {
        if ( refineryEntity == null ) {
            return null;
        }

        Refinery refinery = new Refinery();

        refinery.setId( refineryEntity.getId() );
        refinery.setName( refineryEntity.getName() );
        refinery.setCreatedAt( refineryEntity.getCreatedAt() );

        return refinery;
    }

    protected org.example.model.TransportationType transportationTypeToTransportationType1(TransportationType transportationType) {
        if ( transportationType == null ) {
            return null;
        }

        org.example.model.TransportationType transportationType1;

        switch ( transportationType ) {
            case NORMAL: transportationType1 = org.example.model.TransportationType.NORMAL;
            break;
            case COMMERCIAL: transportationType1 = org.example.model.TransportationType.COMMERCIAL;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + transportationType );
        }

        return transportationType1;
    }

    protected Document documentToDocument1(org.example.repositories.entities.Document document) {
        if ( document == null ) {
            return null;
        }

        Document document1 = new Document();

        document1.setId( document.getId() );
        document1.setUrl( document.getUrl() );
        document1.setType( document.getType() );
        document1.setResourceId( document.getResourceId() );
        document1.setCreatedAt( document.getCreatedAt() );

        return document1;
    }
}
