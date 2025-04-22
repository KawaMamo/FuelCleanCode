package org.example.mappers;

import java.util.Arrays;
import javax.annotation.processing.Generated;
import org.example.entities.DocumentEntity;
import org.example.entities.GasStationEntity;
import org.example.entities.GroupEntity;
import org.example.entities.MaterialEntity;
import org.example.entities.PartitionEntity;
import org.example.entities.PersonEntity;
import org.example.entities.PriceCategoryEntity;
import org.example.entities.RegionEntity;
import org.example.model.Document;
import org.example.model.GasStation;
import org.example.model.Group;
import org.example.model.Material;
import org.example.model.Money;
import org.example.model.Partition;
import org.example.model.Person;
import org.example.model.PriceCategory;
import org.example.model.Region;
import org.mapstruct.factory.Mappers;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-22T19:26:24+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
public class PartitionMapperImpl implements PartitionMapper {

    private final TransMapper transMapper = Mappers.getMapper( TransMapper.class );

    @Override
    public Partition entityToDomain(PartitionEntity partition) {
        if ( partition == null ) {
            return null;
        }

        Partition partition1 = new Partition();

        partition1.setPrice( partitionEntityToMoney( partition ) );
        partition1.setTransportation( transMapper.entityToDomain( partition.getTransportationEntity() ) );
        partition1.setId( partition.getId() );
        partition1.setMaterial( TransMapper.materialEntityToMaterial( partition.getMaterial() ) );
        partition1.setAmount( partition.getAmount() );
        partition1.setCorrectedAmount( partition.getCorrectedAmount() );
        partition1.setGasStation( TransMapper.gasStationEntityToGasStation( partition.getGasStation() ) );
        partition1.setNotes( partition.getNotes() );
        partition1.setExtraNotes( partition.getExtraNotes() );
        partition1.setDocument( TransMapper.documentEntityToDocument( partition.getDocument() ) );
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
        partitionEntity.setTransportationEntity( transMapper.domainToEntity( partition.getTransportation() ) );
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

    protected Money partitionEntityToMoney(PartitionEntity partitionEntity) {
        if ( partitionEntity == null ) {
            return null;
        }

        Money money = new Money();

        money.setCurrency( partitionEntity.getPriceCurrency() );
        money.setAmount( partitionEntity.getPriceAmount() );

        return money;
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
        regionEntity.setPlaceType( region.getPlaceType() );
        regionEntity.setCreatedAt( region.getCreatedAt() );
        regionEntity.setUpdatedAt( region.getUpdatedAt() );
        regionEntity.setName( region.getName() );
        regionEntity.setTranslation( region.getTranslation() );

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
        gasStationEntity.setTranslation( gasStation.getTranslation() );
        gasStationEntity.setPriceCategory( priceCategoryToPriceCategoryEntity( gasStation.getPriceCategory() ) );
        gasStationEntity.setRegion( regionToRegionEntity( gasStation.getRegion() ) );
        gasStationEntity.setOwner( personToPersonEntity( gasStation.getOwner() ) );
        gasStationEntity.setGroup( groupToGroupEntity( gasStation.getGroup() ) );
        gasStationEntity.setMaterial( materialToMaterialEntity( gasStation.getMaterial() ) );

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
}
