package org.example.mappers;

import org.example.entities.*;
import org.example.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.*;

@Mapper(uses = TransLogMapper.class)
public interface TransMapper {

    @Mapping(source = "vehicle", target = "vehicle")
    @Mapping(source = "refinery", target = "refinery")
    @Mapping(source = "document", target = "document")
    @Mapping(source = "partitions", target = "partitionEntities")
    TransportationEntity domainToEntity(Transportation transportation);

    @Mapping(target = "vehicle", source = "vehicle")
    @Mapping(target = "refinery", source = "refinery")
    @Mapping(target = "document", source = "document")
    @Mapping(target = "partitions", source = "partitionEntities", qualifiedByName = "partitionEntityToDomain")
    Transportation entityToDomain(TransportationEntity transportationEntity);






    @Named("partitionEntityToDomain")
    static List<Partition> partitionEntityToDomain(Set<PartitionEntity> partitionEntities){
        List<Partition> partitions = new ArrayList<>();
        if(Objects.isNull(partitionEntities)){
            return partitions;
        }
        for (PartitionEntity partition : partitionEntities) {
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
            partition1.setCreatedAt( partition.getCreatedAt() );
            partition1.setUpdatedAt( partition.getUpdatedAt() );
            partitions.add(partition1);
        }
        return partitions;
    }

    static Money partitionEntityToMoney(PartitionEntity partitionEntity) {
        if ( partitionEntity == null ) {
            return null;
        }

        Money money = new Money();

        money.setCurrency( partitionEntity.getPriceCurrency() );
        money.setAmount( partitionEntity.getPriceAmount() );

        return money;
    }

    static Material materialEntityToMaterial(MaterialEntity materialEntity) {
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

    static GasStation gasStationEntityToGasStation(GasStationEntity gasStationEntity) {
        if ( gasStationEntity == null ) {
            return null;
        }

        GasStation gasStation = new GasStation();

        gasStation.setId( gasStationEntity.getId() );
        gasStation.setName( gasStationEntity.getName() );
        gasStation.setTranslation( gasStationEntity.getTranslation() );
        gasStation.setPlaceType( gasStationEntity.getPlaceType() );
        gasStation.setCreatedAt( gasStationEntity.getCreatedAt() );
        gasStation.setUpdatedAt( gasStationEntity.getUpdatedAt() );
        gasStation.setPriceCategory( priceCategoryEntityToPriceCategory( gasStationEntity.getPriceCategory() ) );
        gasStation.setDebtLimit( gasStationEntity.getDebtLimit() );
        gasStation.setRegion( regionEntityToRegion( gasStationEntity.getRegion() ) );
        gasStation.setOwner( personEntityToPerson( gasStationEntity.getOwner() ) );
        gasStation.setGroup( groupEntityToGroup( gasStationEntity.getGroup() ) );
        gasStation.setMaterial( materialEntityToMaterial( gasStationEntity.getMaterial() ) );

        return gasStation;
    }

    static Document documentEntityToDocument(DocumentEntity documentEntity) {
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

    static PriceCategory priceCategoryEntityToPriceCategory(PriceCategoryEntity priceCategoryEntity) {
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

    static Region regionEntityToRegion(RegionEntity regionEntity) {
        if ( regionEntity == null ) {
            return null;
        }

        Region region = new Region();

        region.setId( regionEntity.getId() );
        region.setName( regionEntity.getName() );
        region.setTranslation( regionEntity.getTranslation() );
        region.setPlaceType( regionEntity.getPlaceType() );
        region.setCreatedAt( regionEntity.getCreatedAt() );
        region.setUpdatedAt( regionEntity.getUpdatedAt() );

        return region;
    }

    static Person personEntityToPerson(PersonEntity personEntity) {
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

    static Group groupEntityToGroup(GroupEntity groupEntity) {
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

}
