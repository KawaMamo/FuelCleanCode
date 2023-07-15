package org.example.mappers;

import java.util.Arrays;
import javax.annotation.processing.Generated;
import org.example.entities.ForfeitEntity;
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
import org.example.entities.TransportationEntity;
import org.example.entities.TransportationType;
import org.example.entities.VehicleEntity;
import org.example.model.Document;
import org.example.model.Forfeit;
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
import org.example.model.Transportation;
import org.example.model.Vehicle;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-15T22:39:41+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
public class ForfeitMapperImpl implements ForfeitMapper {

    @Override
    public Forfeit entityToDomain(ForfeitEntity forfeit) {
        if ( forfeit == null ) {
            return null;
        }

        Forfeit forfeit1 = new Forfeit();

        forfeit1.setId( forfeit.getId() );
        forfeit1.setVehicles( vehicleEntityToVehicle( forfeit.getVehicles() ) );
        forfeit1.setPartition( partitionEntityToPartition( forfeit.getPartition() ) );
        forfeit1.setReason( forfeit.getReason() );
        forfeit1.setCreatedAt( forfeit.getCreatedAt() );

        return forfeit1;
    }

    @Override
    public ForfeitEntity domainToEntity(Forfeit forfeit) {
        if ( forfeit == null ) {
            return null;
        }

        ForfeitEntity forfeitEntity = new ForfeitEntity();

        forfeitEntity.setId( forfeit.getId() );
        forfeitEntity.setVehicles( vehicleToVehicleEntity( forfeit.getVehicles() ) );
        forfeitEntity.setPartition( partitionToPartitionEntity( forfeit.getPartition() ) );
        forfeitEntity.setReason( forfeit.getReason() );
        forfeitEntity.setCreatedAt( forfeit.getCreatedAt() );

        return forfeitEntity;
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

    protected Material materialEntityToMaterial(MaterialEntity materialEntity) {
        if ( materialEntity == null ) {
            return null;
        }

        Material material = new Material();

        material.setId( materialEntity.getId() );
        material.setName( materialEntity.getName() );
        material.setCreatedAt( materialEntity.getCreatedAt() );

        return material;
    }

    protected PriceCategory priceCategoryEntityToPriceCategory(PriceCategoryEntity priceCategoryEntity) {
        if ( priceCategoryEntity == null ) {
            return null;
        }

        PriceCategory priceCategory = new PriceCategory();

        priceCategory.setId( priceCategoryEntity.getId() );
        priceCategory.setName( priceCategoryEntity.getName() );
        priceCategory.setCreatedAt( priceCategoryEntity.getCreatedAt() );

        return priceCategory;
    }

    protected Region regionEntityToRegion(RegionEntity regionEntity) {
        if ( regionEntity == null ) {
            return null;
        }

        Region region = new Region();

        region.setId( regionEntity.getId() );
        region.setName( regionEntity.getName() );
        region.setCreatedAt( regionEntity.getCreatedAt() );

        return region;
    }

    protected Group groupEntityToGroup(GroupEntity groupEntity) {
        if ( groupEntity == null ) {
            return null;
        }

        Group group = new Group();

        group.setId( groupEntity.getId() );
        group.setName( groupEntity.getName() );
        group.setCreatedAt( groupEntity.getCreatedAt() );

        return group;
    }

    protected GasStation gasStationEntityToGasStation(GasStationEntity gasStationEntity) {
        if ( gasStationEntity == null ) {
            return null;
        }

        GasStation gasStation = new GasStation();

        gasStation.setPlaceType( gasStationEntity.getPlaceType() );
        gasStation.setId( gasStationEntity.getId() );
        gasStation.setName( gasStationEntity.getName() );
        gasStation.setPriceCategory( priceCategoryEntityToPriceCategory( gasStationEntity.getPriceCategory() ) );
        gasStation.setDebtLimit( gasStationEntity.getDebtLimit() );
        gasStation.setRegion( regionEntityToRegion( gasStationEntity.getRegion() ) );
        gasStation.setOwner( personEntityToPerson( gasStationEntity.getOwner() ) );
        gasStation.setGroup( groupEntityToGroup( gasStationEntity.getGroup() ) );
        gasStation.setCreatedAt( gasStationEntity.getCreatedAt() );

        return gasStation;
    }

    protected Document documentToDocument(org.example.entities.Document document) {
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

    protected org.example.model.TransportationType transportationTypeToTransportationType(TransportationType transportationType) {
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

    protected Transportation transportationEntityToTransportation(TransportationEntity transportationEntity) {
        if ( transportationEntity == null ) {
            return null;
        }

        Transportation transportation = new Transportation();

        transportation.setId( transportationEntity.getId() );
        transportation.setRefinery( refineryEntityToRefinery( transportationEntity.getRefinery() ) );
        transportation.setIsDivided( transportationEntity.getIsDivided() );
        transportation.setIsPriced( transportationEntity.getIsPriced() );
        transportation.setSize( transportationEntity.getSize() );
        transportation.setCreatedAt( transportationEntity.getCreatedAt() );
        transportation.setType( transportationTypeToTransportationType( transportationEntity.getType() ) );
        transportation.setDocument( documentToDocument( transportationEntity.getDocument() ) );
        transportation.setDeletedAt( transportationEntity.getDeletedAt() );

        return transportation;
    }

    protected Partition partitionEntityToPartition(PartitionEntity partitionEntity) {
        if ( partitionEntity == null ) {
            return null;
        }

        Partition partition = new Partition();

        partition.setId( partitionEntity.getId() );
        partition.setMaterial( materialEntityToMaterial( partitionEntity.getMaterial() ) );
        partition.setAmount( partitionEntity.getAmount() );
        partition.setCorrectedAmount( partitionEntity.getCorrectedAmount() );
        partition.setGasStation( gasStationEntityToGasStation( partitionEntity.getGasStation() ) );
        partition.setNotes( partitionEntity.getNotes() );
        partition.setExtraNotes( partitionEntity.getExtraNotes() );
        partition.setDocument( documentToDocument( partitionEntity.getDocument() ) );
        partition.setTransportation( transportationEntityToTransportation( partitionEntity.getTransportation() ) );
        partition.setCreatedAt( partitionEntity.getCreatedAt() );

        return partition;
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

    protected MaterialEntity materialToMaterialEntity(Material material) {
        if ( material == null ) {
            return null;
        }

        MaterialEntity materialEntity = new MaterialEntity();

        materialEntity.setId( material.getId() );
        materialEntity.setName( material.getName() );
        materialEntity.setCreatedAt( material.getCreatedAt() );

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

        return priceCategoryEntity;
    }

    protected RegionEntity regionToRegionEntity(Region region) {
        if ( region == null ) {
            return null;
        }

        RegionEntity regionEntity = new RegionEntity();

        regionEntity.setId( region.getId() );
        regionEntity.setName( region.getName() );
        regionEntity.setCreatedAt( region.getCreatedAt() );

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

        return groupEntity;
    }

    protected GasStationEntity gasStationToGasStationEntity(GasStation gasStation) {
        if ( gasStation == null ) {
            return null;
        }

        GasStationEntity gasStationEntity = new GasStationEntity();

        gasStationEntity.setId( gasStation.getId() );
        gasStationEntity.setPlaceType( gasStation.getPlaceType() );
        gasStationEntity.setName( gasStation.getName() );
        gasStationEntity.setCreatedAt( gasStation.getCreatedAt() );
        gasStationEntity.setPriceCategory( priceCategoryToPriceCategoryEntity( gasStation.getPriceCategory() ) );
        gasStationEntity.setDebtLimit( gasStation.getDebtLimit() );
        gasStationEntity.setRegion( regionToRegionEntity( gasStation.getRegion() ) );
        gasStationEntity.setOwner( personToPersonEntity( gasStation.getOwner() ) );
        gasStationEntity.setGroup( groupToGroupEntity( gasStation.getGroup() ) );

        return gasStationEntity;
    }

    protected org.example.entities.Document documentToDocument1(Document document) {
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

    protected TransportationType transportationTypeToTransportationType1(org.example.model.TransportationType transportationType) {
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

    protected TransportationEntity transportationToTransportationEntity(Transportation transportation) {
        if ( transportation == null ) {
            return null;
        }

        TransportationEntity transportationEntity = new TransportationEntity();

        transportationEntity.setId( transportation.getId() );
        transportationEntity.setRefinery( refineryToRefineryEntity( transportation.getRefinery() ) );
        transportationEntity.setIsDivided( transportation.getIsDivided() );
        transportationEntity.setIsPriced( transportation.getIsPriced() );
        transportationEntity.setSize( transportation.getSize() );
        transportationEntity.setCreatedAt( transportation.getCreatedAt() );
        transportationEntity.setType( transportationTypeToTransportationType1( transportation.getType() ) );
        transportationEntity.setDocument( documentToDocument1( transportation.getDocument() ) );
        transportationEntity.setDeletedAt( transportation.getDeletedAt() );

        return transportationEntity;
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
        partitionEntity.setDocument( documentToDocument1( partition.getDocument() ) );
        partitionEntity.setCreatedAt( partition.getCreatedAt() );
        partitionEntity.setTransportation( transportationToTransportationEntity( partition.getTransportation() ) );

        return partitionEntity;
    }
}
