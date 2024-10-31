package org.example.mappers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.example.entities.DocumentEntity;
import org.example.entities.GasStationEntity;
import org.example.entities.GroupEntity;
import org.example.entities.MaterialEntity;
import org.example.entities.OfficeEntity;
import org.example.entities.PartitionEntity;
import org.example.entities.PersonEntity;
import org.example.entities.PriceCategoryEntity;
import org.example.entities.RefineryEntity;
import org.example.entities.RegionEntity;
import org.example.entities.TrafficCenterEntity;
import org.example.entities.TransLogEntity;
import org.example.entities.TransportationEntity;
import org.example.entities.TransportationType;
import org.example.entities.VehicleEntity;
import org.example.model.Document;
import org.example.model.GasStation;
import org.example.model.Group;
import org.example.model.Material;
import org.example.model.Office;
import org.example.model.Partition;
import org.example.model.Person;
import org.example.model.PriceCategory;
import org.example.model.Refinery;
import org.example.model.Region;
import org.example.model.TrafficCenter;
import org.example.model.TransLog;
import org.example.model.Transportation;
import org.example.model.Vehicle;
import org.mapstruct.factory.Mappers;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-31T23:55:24+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
public class TransMapperImpl implements TransMapper {

    private final TransLogMapper transLogMapper = Mappers.getMapper( TransLogMapper.class );

    @Override
    public TransportationEntity domainToEntity(Transportation transportation) {
        if ( transportation == null ) {
            return null;
        }

        TransportationEntity transportationEntity = new TransportationEntity();

        transportationEntity.setVehicle( vehicleToVehicleEntity( transportation.getVehicle() ) );
        transportationEntity.setRefinery( refineryToRefineryEntity( transportation.getRefinery() ) );
        transportationEntity.setDocument( documentListToDocumentEntityList( transportation.getDocument() ) );
        transportationEntity.setPartitionEntities( partitionListToPartitionEntitySet( transportation.getPartitions() ) );
        transportationEntity.setId( transportation.getId() );
        transportationEntity.setIsDivided( transportation.getIsDivided() );
        transportationEntity.setIsPriced( transportation.getIsPriced() );
        transportationEntity.setSize( transportation.getSize() );
        transportationEntity.setCreatedAt( transportation.getCreatedAt() );
        transportationEntity.setUpdatedAt( transportation.getUpdatedAt() );
        transportationEntity.setType( transportationTypeToTransportationType( transportation.getType() ) );
        transportationEntity.setDeletedAt( transportation.getDeletedAt() );
        transportationEntity.setTransLogs( transLogListToTransLogEntitySet( transportation.getTransLogs() ) );

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
        transportation.setDocument( documentEntityListToDocumentList( transportationEntity.getDocument() ) );
        transportation.setPartitions( TransMapper.partitionEntityToDomain( transportationEntity.getPartitionEntities() ) );
        transportation.setId( transportationEntity.getId() );
        transportation.setIsDivided( transportationEntity.getIsDivided() );
        transportation.setIsPriced( transportationEntity.getIsPriced() );
        transportation.setSize( transportationEntity.getSize() );
        transportation.setCreatedAt( transportationEntity.getCreatedAt() );
        transportation.setUpdatedAt( transportationEntity.getUpdatedAt() );
        transportation.setTransLogs( transLogEntitySetToTransLogList( transportationEntity.getTransLogs() ) );
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
        trafficCenterEntity.setUpdatedAt( trafficCenter.getUpdatedAt() );

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
        officeEntity.setUpdateAt( office.getUpdateAt() );

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
        personEntity.setUpdatedAt( person.getUpdatedAt() );

        return personEntity;
    }

    protected VehicleEntity vehicleToVehicleEntity(Vehicle vehicle) {
        if ( vehicle == null ) {
            return null;
        }

        VehicleEntity vehicleEntity = new VehicleEntity();

        vehicleEntity.setId( vehicle.getId() );
        vehicleEntity.setTurn( vehicle.getTurn() );
        vehicleEntity.setPlateNumber( vehicle.getPlateNumber() );
        vehicleEntity.setTrafficCenter( trafficCenterToTrafficCenterEntity( vehicle.getTrafficCenter() ) );
        vehicleEntity.setSize( vehicle.getSize() );
        vehicleEntity.setOffice( officeToOfficeEntity( vehicle.getOffice() ) );
        vehicleEntity.setDriver( personToPersonEntity( vehicle.getDriver() ) );
        vehicleEntity.setCreatedAt( vehicle.getCreatedAt() );
        vehicleEntity.setUpdatedAt( vehicle.getUpdatedAt() );

        return vehicleEntity;
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

    protected MaterialEntity materialToMaterialEntity(Material material) {
        if ( material == null ) {
            return null;
        }

        MaterialEntity materialEntity = new MaterialEntity();

        materialEntity.setId( material.getId() );
        materialEntity.setName( material.getName() );
        materialEntity.setCreatedAt( material.getCreatedAt() );
        materialEntity.setUpdatedAt( material.getUpdatedAt() );

        return materialEntity;
    }

    protected PriceCategoryEntity priceCategoryToPriceCategoryEntity(PriceCategory priceCategory) {
        if ( priceCategory == null ) {
            return null;
        }

        PriceCategoryEntity priceCategoryEntity = new PriceCategoryEntity();

        priceCategoryEntity.setId( priceCategory.getId() );
        priceCategoryEntity.setName( priceCategory.getName() );
        priceCategoryEntity.setCreatedAt( priceCategory.getCreatedAt() );
        priceCategoryEntity.setUpdatedAt( priceCategory.getUpdatedAt() );

        return priceCategoryEntity;
    }

    protected RegionEntity regionToRegionEntity(Region region) {
        if ( region == null ) {
            return null;
        }

        RegionEntity regionEntity = new RegionEntity();

        regionEntity.setId( region.getId() );
        regionEntity.setPlaceType( region.getPlaceType() );
        regionEntity.setCreatedAt( region.getCreatedAt() );
        regionEntity.setUpdatedAt( region.getUpdatedAt() );
        regionEntity.setName( region.getName() );
        regionEntity.setTranslation( region.getTranslation() );

        return regionEntity;
    }

    protected GroupEntity groupToGroupEntity(Group group) {
        if ( group == null ) {
            return null;
        }

        GroupEntity groupEntity = new GroupEntity();

        groupEntity.setId( group.getId() );
        groupEntity.setName( group.getName() );
        groupEntity.setCreatedAt( group.getCreatedAt() );
        groupEntity.setUpdatedAt( group.getUpdatedAt() );

        return groupEntity;
    }

    protected GasStationEntity gasStationToGasStationEntity(GasStation gasStation) {
        if ( gasStation == null ) {
            return null;
        }

        GasStationEntity gasStationEntity = new GasStationEntity();

        gasStationEntity.setId( gasStation.getId() );
        gasStationEntity.setPlaceType( gasStation.getPlaceType() );
        gasStationEntity.setCreatedAt( gasStation.getCreatedAt() );
        gasStationEntity.setUpdatedAt( gasStation.getUpdatedAt() );
        gasStationEntity.setName( gasStation.getName() );
        gasStationEntity.setTranslation( gasStation.getTranslation() );
        gasStationEntity.setPriceCategory( priceCategoryToPriceCategoryEntity( gasStation.getPriceCategory() ) );
        gasStationEntity.setRegion( regionToRegionEntity( gasStation.getRegion() ) );
        gasStationEntity.setOwner( personToPersonEntity( gasStation.getOwner() ) );
        gasStationEntity.setGroup( groupToGroupEntity( gasStation.getGroup() ) );
        gasStationEntity.setMaterial( materialToMaterialEntity( gasStation.getMaterial() ) );

        return gasStationEntity;
    }

    protected PartitionEntity partitionToPartitionEntity(Partition partition) {
        if ( partition == null ) {
            return null;
        }

        PartitionEntity partitionEntity = new PartitionEntity();

        partitionEntity.setId( partition.getId() );
        partitionEntity.setMaterial( materialToMaterialEntity( partition.getMaterial() ) );
        partitionEntity.setAmount( partition.getAmount() );
        partitionEntity.setCorrectedAmount( partition.getCorrectedAmount() );
        partitionEntity.setGasStation( gasStationToGasStationEntity( partition.getGasStation() ) );
        partitionEntity.setNotes( partition.getNotes() );
        partitionEntity.setExtraNotes( partition.getExtraNotes() );
        partitionEntity.setDocument( documentToDocumentEntity( partition.getDocument() ) );
        partitionEntity.setCreatedAt( partition.getCreatedAt() );
        partitionEntity.setUpdatedAt( partition.getUpdatedAt() );

        return partitionEntity;
    }

    protected Set<PartitionEntity> partitionListToPartitionEntitySet(List<Partition> list) {
        if ( list == null ) {
            return null;
        }

        Set<PartitionEntity> set = new LinkedHashSet<PartitionEntity>( Math.max( (int) ( list.size() / .75f ) + 1, 16 ) );
        for ( Partition partition : list ) {
            set.add( partitionToPartitionEntity( partition ) );
        }

        return set;
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

    protected Vehicle vehicleEntityToVehicle(VehicleEntity vehicleEntity) {
        if ( vehicleEntity == null ) {
            return null;
        }

        Vehicle vehicle = new Vehicle();

        vehicle.setId( vehicleEntity.getId() );
        vehicle.setTurn( vehicleEntity.getTurn() );
        vehicle.setPlateNumber( vehicleEntity.getPlateNumber() );
        vehicle.setTrafficCenter( trafficCenterEntityToTrafficCenter( vehicleEntity.getTrafficCenter() ) );
        vehicle.setSize( vehicleEntity.getSize() );
        vehicle.setOffice( officeEntityToOffice( vehicleEntity.getOffice() ) );
        vehicle.setDriver( TransMapper.personEntityToPerson( vehicleEntity.getDriver() ) );
        vehicle.setCreatedAt( vehicleEntity.getCreatedAt() );
        vehicle.setUpdatedAt( vehicleEntity.getUpdatedAt() );

        return vehicle;
    }

    protected Refinery refineryEntityToRefinery(RefineryEntity refineryEntity) {
        if ( refineryEntity == null ) {
            return null;
        }

        Refinery refinery = new Refinery();

        refinery.setId( refineryEntity.getId() );
        refinery.setName( refineryEntity.getName() );
        refinery.setTranslation( refineryEntity.getTranslation() );
        refinery.setPlaceType( refineryEntity.getPlaceType() );
        refinery.setCreatedAt( refineryEntity.getCreatedAt() );
        refinery.setUpdatedAt( refineryEntity.getUpdatedAt() );

        return refinery;
    }

    protected List<Document> documentEntityListToDocumentList(List<DocumentEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<Document> list1 = new ArrayList<Document>( list.size() );
        for ( DocumentEntity documentEntity : list ) {
            list1.add( TransMapper.documentEntityToDocument( documentEntity ) );
        }

        return list1;
    }

    protected List<TransLog> transLogEntitySetToTransLogList(Set<TransLogEntity> set) {
        if ( set == null ) {
            return null;
        }

        List<TransLog> list = new ArrayList<TransLog>( set.size() );
        for ( TransLogEntity transLogEntity : set ) {
            list.add( transLogMapper.entityToDomain( transLogEntity ) );
        }

        return list;
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
