package org.example.mappers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.example.entities.BuyOperation;
import org.example.entities.DocumentEntity;
import org.example.entities.OfficeEntity;
import org.example.entities.PersonEntity;
import org.example.entities.RefineryEntity;
import org.example.entities.TrafficCenterEntity;
import org.example.entities.TransLogEntity;
import org.example.entities.TransportationType;
import org.example.entities.VehicleEntity;
import org.example.model.Document;
import org.example.model.Office;
import org.example.model.Person;
import org.example.model.Refinery;
import org.example.model.TrafficCenter;
import org.example.model.TransLog;
import org.example.model.Vehicle;
import org.mapstruct.factory.Mappers;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-29T20:43:08+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
public class VehicleMapperImpl implements VehicleMapper {

    private final TransLogMapper transLogMapper = Mappers.getMapper( TransLogMapper.class );

    @Override
    public VehicleEntity domainToEntity(Vehicle vehicle) {
        if ( vehicle == null ) {
            return null;
        }

        VehicleEntity vehicleEntity = new VehicleEntity();

        vehicleEntity.setId( vehicle.getId() );
        vehicleEntity.setTurn( vehicle.getTurn() );
        vehicleEntity.setPlateNumber( vehicle.getPlateNumber() );
        vehicleEntity.setTrafficCenter( domainToEntity( vehicle.getTrafficCenter() ) );
        vehicleEntity.setSize( vehicle.getSize() );
        vehicleEntity.setOffice( domainToEntity( vehicle.getOffice() ) );
        vehicleEntity.setDriver( domainToEntity( vehicle.getDriver() ) );
        vehicleEntity.setCreatedAt( vehicle.getCreatedAt() );
        vehicleEntity.setUpdatedAt( vehicle.getUpdatedAt() );

        return vehicleEntity;
    }

    @Override
    public PersonEntity domainToEntity(Person person) {
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
        personEntity.setUpdatedAt( person.getUpdatedAt() );

        return personEntity;
    }

    @Override
    public BuyOperation domainToEntity(org.example.model.BuyOperation buyOperation) {
        if ( buyOperation == null ) {
            return null;
        }

        BuyOperation buyOperation1 = new BuyOperation();

        buyOperation1.setId( buyOperation.getId() );
        buyOperation1.setVehicle( domainToEntity( buyOperation.getVehicle() ) );
        buyOperation1.setRefinery( refineryToRefineryEntity( buyOperation.getRefinery() ) );
        buyOperation1.setIsDivided( buyOperation.getIsDivided() );
        buyOperation1.setIsPriced( buyOperation.getIsPriced() );
        buyOperation1.setSize( buyOperation.getSize() );
        buyOperation1.setCreatedAt( buyOperation.getCreatedAt() );
        buyOperation1.setUpdatedAt( buyOperation.getUpdatedAt() );
        buyOperation1.setType( transportationTypeToTransportationType( buyOperation.getType() ) );
        buyOperation1.setDocument( documentListToDocumentEntityList( buyOperation.getDocument() ) );
        buyOperation1.setDeletedAt( buyOperation.getDeletedAt() );
        buyOperation1.setTransLogs( transLogListToTransLogEntitySet( buyOperation.getTransLogs() ) );
        buyOperation1.setSource( buyOperation.getSource() );

        return buyOperation1;
    }

    @Override
    public Vehicle entityToDomain(VehicleEntity save) {
        if ( save == null ) {
            return null;
        }

        Vehicle vehicle = new Vehicle();

        vehicle.setId( save.getId() );
        vehicle.setTurn( save.getTurn() );
        vehicle.setPlateNumber( save.getPlateNumber() );
        vehicle.setTrafficCenter( trafficCenterEntityToTrafficCenter( save.getTrafficCenter() ) );
        vehicle.setSize( save.getSize() );
        vehicle.setOffice( officeEntityToOffice( save.getOffice() ) );
        vehicle.setDriver( personEntityToPerson( save.getDriver() ) );
        vehicle.setCreatedAt( save.getCreatedAt() );
        vehicle.setUpdatedAt( save.getUpdatedAt() );

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
        officeEntity.setUpdateAt( office.getUpdateAt() );

        return officeEntity;
    }

    @Override
    public TrafficCenterEntity domainToEntity(TrafficCenter trafficCenter) {
        if ( trafficCenter == null ) {
            return null;
        }

        TrafficCenterEntity trafficCenterEntity = new TrafficCenterEntity();

        trafficCenterEntity.setId( trafficCenter.getId() );
        trafficCenterEntity.setName( trafficCenter.getName() );
        trafficCenterEntity.setCreatedAt( trafficCenter.getCreatedAt() );
        trafficCenterEntity.setUpdatedAt( trafficCenter.getUpdatedAt() );

        return trafficCenterEntity;
    }

    protected RefineryEntity refineryToRefineryEntity(Refinery refinery) {
        if ( refinery == null ) {
            return null;
        }

        RefineryEntity refineryEntity = new RefineryEntity();

        refineryEntity.setId( refinery.getId() );
        refineryEntity.setPlaceType( refinery.getPlaceType() );
        refineryEntity.setCreatedAt( refinery.getCreatedAt() );
        refineryEntity.setUpdatedAt( refinery.getUpdatedAt() );
        refineryEntity.setName( refinery.getName() );
        refineryEntity.setTranslation( refinery.getTranslation() );

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

    protected DocumentEntity documentToDocumentEntity(Document document) {
        if ( document == null ) {
            return null;
        }

        DocumentEntity documentEntity = new DocumentEntity();

        documentEntity.setId( document.getId() );
        documentEntity.setUrl( document.getUrl() );
        documentEntity.setType( document.getType() );
        documentEntity.setResourceId( document.getResourceId() );
        byte[] content = document.getContent();
        if ( content != null ) {
            documentEntity.setContent( Arrays.copyOf( content, content.length ) );
        }
        documentEntity.setCreatedAt( document.getCreatedAt() );
        documentEntity.setUpdatedAt( document.getUpdatedAt() );

        return documentEntity;
    }

    protected List<DocumentEntity> documentListToDocumentEntityList(List<Document> list) {
        if ( list == null ) {
            return null;
        }

        List<DocumentEntity> list1 = new ArrayList<DocumentEntity>( list.size() );
        for ( Document document : list ) {
            list1.add( documentToDocumentEntity( document ) );
        }

        return list1;
    }

    protected Set<TransLogEntity> transLogListToTransLogEntitySet(List<TransLog> list) {
        if ( list == null ) {
            return null;
        }

        Set<TransLogEntity> set = new LinkedHashSet<TransLogEntity>( Math.max( (int) ( list.size() / .75f ) + 1, 16 ) );
        for ( TransLog transLog : list ) {
            set.add( transLogMapper.domainToEntity( transLog ) );
        }

        return set;
    }

    protected TrafficCenter trafficCenterEntityToTrafficCenter(TrafficCenterEntity trafficCenterEntity) {
        if ( trafficCenterEntity == null ) {
            return null;
        }

        TrafficCenter trafficCenter = new TrafficCenter();

        trafficCenter.setId( trafficCenterEntity.getId() );
        trafficCenter.setName( trafficCenterEntity.getName() );
        trafficCenter.setCreatedAt( trafficCenterEntity.getCreatedAt() );
        trafficCenter.setUpdatedAt( trafficCenterEntity.getUpdatedAt() );

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
        office.setUpdateAt( officeEntity.getUpdateAt() );

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
        person.setUpdatedAt( personEntity.getUpdatedAt() );

        return person;
    }
}
