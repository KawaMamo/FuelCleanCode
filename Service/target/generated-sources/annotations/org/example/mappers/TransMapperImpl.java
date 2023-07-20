package org.example.mappers;

import java.util.Arrays;
import javax.annotation.processing.Generated;
import org.example.entities.OfficeEntity;
import org.example.entities.PersonEntity;
import org.example.entities.RefineryEntity;
import org.example.entities.TrafficCenterEntity;
import org.example.entities.TransportationEntity;
import org.example.entities.TransportationType;
import org.example.entities.VehicleEntity;
import org.example.model.Document;
import org.example.model.Office;
import org.example.model.Person;
import org.example.model.Refinery;
import org.example.model.TrafficCenter;
import org.example.model.Transportation;
import org.example.model.Vehicle;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-19T23:30:12+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
public class TransMapperImpl implements TransMapper {

    @Override
    public TransportationEntity domainToEntity(Transportation transportation) {
        if ( transportation == null ) {
            return null;
        }

        TransportationEntity transportationEntity = new TransportationEntity();

        transportationEntity.setVehicle( vehicleToVehicleEntity( transportation.getVehicle() ) );
        transportationEntity.setRefinery( refineryToRefineryEntity( transportation.getRefinery() ) );
        transportationEntity.setDocument( documentToDocument( transportation.getDocument() ) );
        transportationEntity.setId( transportation.getId() );
        transportationEntity.setIsDivided( transportation.getIsDivided() );
        transportationEntity.setIsPriced( transportation.getIsPriced() );
        transportationEntity.setSize( transportation.getSize() );
        transportationEntity.setCreatedAt( transportation.getCreatedAt() );
        transportationEntity.setType( transportationTypeToTransportationType( transportation.getType() ) );
        transportationEntity.setDeletedAt( transportation.getDeletedAt() );

        return transportationEntity;
    }

    @Override
    public Transportation entityToDomain(TransportationEntity transportationEntity) {
        if ( transportationEntity == null ) {
            return null;
        }

        Transportation transportation = new Transportation();

        transportation.setVehicle( vehicleEntityToVehicle( transportationEntity.getVehicle() ) );
        transportation.setRefinery( refineryEntityToRefinery( transportationEntity.getRefinery() ) );
        transportation.setDocument( documentToDocument1( transportationEntity.getDocument() ) );
        transportation.setId( transportationEntity.getId() );
        transportation.setIsDivided( transportationEntity.getIsDivided() );
        transportation.setIsPriced( transportationEntity.getIsPriced() );
        transportation.setSize( transportationEntity.getSize() );
        transportation.setCreatedAt( transportationEntity.getCreatedAt() );
        transportation.setType( transportationTypeToTransportationType1( transportationEntity.getType() ) );
        transportation.setDeletedAt( transportationEntity.getDeletedAt() );

        return transportation;
    }

    protected TrafficCenterEntity trafficCenterToTrafficCenterEntity(TrafficCenter trafficCenter) {
        if ( trafficCenter == null ) {
            return null;
        }

        TrafficCenterEntity trafficCenterEntity = new TrafficCenterEntity();

        trafficCenterEntity.setId( trafficCenter.getId() );
        trafficCenterEntity.setName( trafficCenter.getName() );
        trafficCenterEntity.setCreatedAt( trafficCenter.getCreatedAt() );

        return trafficCenterEntity;
    }

    protected OfficeEntity officeToOfficeEntity(Office office) {
        if ( office == null ) {
            return null;
        }

        OfficeEntity officeEntity = new OfficeEntity();

        officeEntity.setId( office.getId() );
        officeEntity.setName( office.getName() );
        officeEntity.setCreatedAt( office.getCreatedAt() );

        return officeEntity;
    }

    protected PersonEntity personToPersonEntity(Person person) {
        if ( person == null ) {
            return null;
        }

        PersonEntity personEntity = new PersonEntity();

        personEntity.setId( person.getId() );
        personEntity.setName( person.getName() );
        personEntity.setFather( person.getFather() );
        personEntity.setMother( person.getMother() );
        personEntity.setNationalId( person.getNationalId() );
        personEntity.setBirthPlace( person.getBirthPlace() );
        personEntity.setBirthDate( person.getBirthDate() );
        personEntity.setCreatedAt( person.getCreatedAt() );

        return personEntity;
    }

    protected VehicleEntity vehicleToVehicleEntity(Vehicle vehicle) {
        if ( vehicle == null ) {
            return null;
        }

        VehicleEntity vehicleEntity = new VehicleEntity();

        vehicleEntity.setId( vehicle.getId() );
        vehicleEntity.setPlateNumber( vehicle.getPlateNumber() );
        vehicleEntity.setTrafficCenter( trafficCenterToTrafficCenterEntity( vehicle.getTrafficCenter() ) );
        vehicleEntity.setSize( vehicle.getSize() );
        vehicleEntity.setOffice( officeToOfficeEntity( vehicle.getOffice() ) );
        vehicleEntity.setDriver( personToPersonEntity( vehicle.getDriver() ) );
        vehicleEntity.setCreatedAt( vehicle.getCreatedAt() );

        return vehicleEntity;
    }

    protected RefineryEntity refineryToRefineryEntity(Refinery refinery) {
        if ( refinery == null ) {
            return null;
        }

        RefineryEntity refineryEntity = new RefineryEntity();

        refineryEntity.setId( refinery.getId() );
        refineryEntity.setPlaceType( refinery.getPlaceType() );
        refineryEntity.setName( refinery.getName() );
        refineryEntity.setCreatedAt( refinery.getCreatedAt() );

        return refineryEntity;
    }

    protected org.example.entities.Document documentToDocument(Document document) {
        if ( document == null ) {
            return null;
        }

        org.example.entities.Document document1 = new org.example.entities.Document();

        document1.setId( document.getId() );
        document1.setUrl( document.getUrl() );
        document1.setType( document.getType() );
        document1.setResourceId( document.getResourceId() );
        byte[] content = document.getContent();
        if ( content != null ) {
            document1.setContent( Arrays.copyOf( content, content.length ) );
        }
        document1.setCreatedAt( document.getCreatedAt() );

        return document1;
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

    protected TrafficCenter trafficCenterEntityToTrafficCenter(TrafficCenterEntity trafficCenterEntity) {
        if ( trafficCenterEntity == null ) {
            return null;
        }

        TrafficCenter trafficCenter = new TrafficCenter();

        trafficCenter.setId( trafficCenterEntity.getId() );
        trafficCenter.setName( trafficCenterEntity.getName() );
        trafficCenter.setCreatedAt( trafficCenterEntity.getCreatedAt() );

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

        person.setId( personEntity.getId() );
        person.setName( personEntity.getName() );
        person.setFather( personEntity.getFather() );
        person.setMother( personEntity.getMother() );
        person.setNationalId( personEntity.getNationalId() );
        person.setBirthPlace( personEntity.getBirthPlace() );
        person.setBirthDate( personEntity.getBirthDate() );
        person.setCreatedAt( personEntity.getCreatedAt() );

        return person;
    }

    protected Vehicle vehicleEntityToVehicle(VehicleEntity vehicleEntity) {
        if ( vehicleEntity == null ) {
            return null;
        }

        Vehicle vehicle = new Vehicle();

        vehicle.setId( vehicleEntity.getId() );
        vehicle.setPlateNumber( vehicleEntity.getPlateNumber() );
        vehicle.setTrafficCenter( trafficCenterEntityToTrafficCenter( vehicleEntity.getTrafficCenter() ) );
        vehicle.setSize( vehicleEntity.getSize() );
        vehicle.setOffice( officeEntityToOffice( vehicleEntity.getOffice() ) );
        vehicle.setDriver( personEntityToPerson( vehicleEntity.getDriver() ) );
        vehicle.setCreatedAt( vehicleEntity.getCreatedAt() );

        return vehicle;
    }

    protected Refinery refineryEntityToRefinery(RefineryEntity refineryEntity) {
        if ( refineryEntity == null ) {
            return null;
        }

        Refinery refinery = new Refinery();

        refinery.setPlaceType( refineryEntity.getPlaceType() );
        refinery.setId( refineryEntity.getId() );
        refinery.setName( refineryEntity.getName() );
        refinery.setCreatedAt( refineryEntity.getCreatedAt() );

        return refinery;
    }

    protected Document documentToDocument1(org.example.entities.Document document) {
        if ( document == null ) {
            return null;
        }

        Document document1 = new Document();

        document1.setId( document.getId() );
        document1.setUrl( document.getUrl() );
        document1.setType( document.getType() );
        document1.setResourceId( document.getResourceId() );
        byte[] content = document.getContent();
        if ( content != null ) {
            document1.setContent( Arrays.copyOf( content, content.length ) );
        }
        document1.setCreatedAt( document.getCreatedAt() );

        return document1;
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
}
