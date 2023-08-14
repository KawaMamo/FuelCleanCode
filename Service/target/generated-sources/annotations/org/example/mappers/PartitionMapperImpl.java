package org.example.mappers;

import java.util.Arrays;
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
import org.example.entities.TransportationEntity;
import org.example.entities.TransportationType;
import org.example.entities.VehicleEntity;
import org.example.model.Document;
import org.example.model.GasStation;
import org.example.model.Group;
import org.example.model.Material;
import org.example.model.Money;
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
    date = "2023-08-14T14:36:30+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
public class PartitionMapperImpl implements PartitionMapper {

    @Override
    public Partition entityToDomain(PartitionEntity partition) {
        if ( partition == null ) {
            return null;
        }

        Partition partition1 = new Partition();

        partition1.setPrice( partitionEntityToMoney( partition ) );
        partition1.setId( partition.getId() );
        partition1.setMaterial( materialEntityToMaterial( partition.getMaterial() ) );
        partition1.setAmount( partition.getAmount() );
        partition1.setCorrectedAmount( partition.getCorrectedAmount() );
        partition1.setGasStation( gasStationEntityToGasStation( partition.getGasStation() ) );
        partition1.setNotes( partition.getNotes() );
        partition1.setExtraNotes( partition.getExtraNotes() );
        partition1.setDocument( documentEntityToDocument( partition.getDocument() ) );
        partition1.setTransportation( transportationEntityToTransportation( partition.getTransportation() ) );
        partition1.setCreatedAt( partition.getCreatedAt() );
        partition1.setUpdatedAt( partition.getUpdatedAt() );

        return partition1;
    }

    @Override
    public PartitionEntity domainToEntity(Partition partition) {
        if ( partition == null ) {
            return null;
        }

        PartitionEntity partitionEntity = new PartitionEntity();

        partitionEntity.setPriceCurrency( partitionPriceCurrency( partition ) );
        partitionEntity.setPriceAmount( partitionPriceAmount( partition ) );
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
        partitionEntity.setTransportation( transportationToTransportationEntity( partition.getTransportation() ) );

        return partitionEntity;
    }

    protected Money partitionEntityToMoney(PartitionEntity partitionEntity) {
        if ( partitionEntity == null ) {
            return null;
        }

        Money money = new Money();

        money.setCurrency( partitionEntity.getPriceCurrency() );
        money.setAmount( partitionEntity.getPriceAmount() );

        return money;
    }

    protected Material materialEntityToMaterial(MaterialEntity materialEntity) {
        if ( materialEntity == null ) {
            return null;
        }

        Material material = new Material();

        material.setId( materialEntity.getId() );
        material.setName( materialEntity.getName() );
        material.setCreatedAt( materialEntity.getCreatedAt() );
        material.setUpdatedAt( materialEntity.getUpdatedAt() );

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
        priceCategory.setUpdatedAt( priceCategoryEntity.getUpdatedAt() );

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
        region.setUpdatedAt( regionEntity.getUpdatedAt() );

        return region;
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

    protected Group groupEntityToGroup(GroupEntity groupEntity) {
        if ( groupEntity == null ) {
            return null;
        }

        Group group = new Group();

        group.setId( groupEntity.getId() );
        group.setName( groupEntity.getName() );
        group.setCreatedAt( groupEntity.getCreatedAt() );
        group.setUpdatedAt( groupEntity.getUpdatedAt() );

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
        gasStation.setUpdatedAt( gasStationEntity.getUpdatedAt() );

        return gasStation;
    }

    protected Document documentEntityToDocument(DocumentEntity documentEntity) {
        if ( documentEntity == null ) {
            return null;
        }

        Document document = new Document();

        document.setId( documentEntity.getId() );
        document.setUrl( documentEntity.getUrl() );
        document.setType( documentEntity.getType() );
        document.setResourceId( documentEntity.getResourceId() );
        byte[] content = documentEntity.getContent();
        if ( content != null ) {
            document.setContent( Arrays.copyOf( content, content.length ) );
        }
        document.setCreatedAt( documentEntity.getCreatedAt() );
        document.setUpdatedAt( documentEntity.getUpdatedAt() );

        return document;
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
        vehicle.setPlateNumber( vehicleEntity.getPlateNumber() );
        vehicle.setTrafficCenter( trafficCenterEntityToTrafficCenter( vehicleEntity.getTrafficCenter() ) );
        vehicle.setSize( vehicleEntity.getSize() );
        vehicle.setOffice( officeEntityToOffice( vehicleEntity.getOffice() ) );
        vehicle.setDriver( personEntityToPerson( vehicleEntity.getDriver() ) );
        vehicle.setCreatedAt( vehicleEntity.getCreatedAt() );
        vehicle.setUpdatedAt( vehicleEntity.getUpdatedAt() );

        return vehicle;
    }

    protected Refinery refineryEntityToRefinery(RefineryEntity refineryEntity) {
        if ( refineryEntity == null ) {
            return null;
        }

        Refinery refinery = new Refinery();

        refinery.setPlaceType( refineryEntity.getPlaceType() );
        refinery.setCreatedAt( refineryEntity.getCreatedAt() );
        refinery.setUpdatedAt( refineryEntity.getUpdatedAt() );
        refinery.setId( refineryEntity.getId() );
        refinery.setName( refineryEntity.getName() );

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
        transportation.setVehicle( vehicleEntityToVehicle( transportationEntity.getVehicle() ) );
        transportation.setRefinery( refineryEntityToRefinery( transportationEntity.getRefinery() ) );
        transportation.setIsDivided( transportationEntity.getIsDivided() );
        transportation.setIsPriced( transportationEntity.getIsPriced() );
        transportation.setSize( transportationEntity.getSize() );
        transportation.setCreatedAt( transportationEntity.getCreatedAt() );
        transportation.setUpdatedAt( transportationEntity.getUpdatedAt() );
        transportation.setType( transportationTypeToTransportationType( transportationEntity.getType() ) );
        transportation.setDocument( documentEntityToDocument( transportationEntity.getDocument() ) );
        transportation.setDeletedAt( transportationEntity.getDeletedAt() );

        return transportation;
    }

    private String partitionPriceCurrency(Partition partition) {
        if ( partition == null ) {
            return null;
        }
        Money price = partition.getPrice();
        if ( price == null ) {
            return null;
        }
        String currency = price.getCurrency();
        if ( currency == null ) {
            return null;
        }
        return currency;
    }

    private Double partitionPriceAmount(Partition partition) {
        if ( partition == null ) {
            return null;
        }
        Money price = partition.getPrice();
        if ( price == null ) {
            return null;
        }
        Double amount = price.getAmount();
        if ( amount == null ) {
            return null;
        }
        return amount;
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
        regionEntity.setName( region.getName() );
        regionEntity.setCreatedAt( region.getCreatedAt() );
        regionEntity.setUpdatedAt( region.getUpdatedAt() );

        return regionEntity;
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
        gasStationEntity.setPriceCategory( priceCategoryToPriceCategoryEntity( gasStation.getPriceCategory() ) );
        gasStationEntity.setDebtLimit( gasStation.getDebtLimit() );
        gasStationEntity.setRegion( regionToRegionEntity( gasStation.getRegion() ) );
        gasStationEntity.setOwner( personToPersonEntity( gasStation.getOwner() ) );
        gasStationEntity.setGroup( groupToGroupEntity( gasStation.getGroup() ) );

        return gasStationEntity;
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
        transportationEntity.setVehicle( vehicleToVehicleEntity( transportation.getVehicle() ) );
        transportationEntity.setRefinery( refineryToRefineryEntity( transportation.getRefinery() ) );
        transportationEntity.setIsDivided( transportation.getIsDivided() );
        transportationEntity.setIsPriced( transportation.getIsPriced() );
        transportationEntity.setSize( transportation.getSize() );
        transportationEntity.setCreatedAt( transportation.getCreatedAt() );
        transportationEntity.setUpdatedAt( transportation.getUpdatedAt() );
        transportationEntity.setType( transportationTypeToTransportationType1( transportation.getType() ) );
        transportationEntity.setDocument( documentToDocumentEntity( transportation.getDocument() ) );
        transportationEntity.setDeletedAt( transportation.getDeletedAt() );

        return transportationEntity;
    }
}
