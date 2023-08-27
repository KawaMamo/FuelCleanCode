package org.example.mappers;

import javax.annotation.processing.Generated;
import org.example.entities.GasStationEntity;
import org.example.entities.GroupEntity;
import org.example.entities.MaterialEntity;
import org.example.entities.PersonEntity;
import org.example.entities.PriceCategoryEntity;
import org.example.entities.RegionEntity;
import org.example.entities.TransferMaterialsEntity;
import org.example.model.GasStation;
import org.example.model.Group;
import org.example.model.Material;
import org.example.model.Money;
import org.example.model.Person;
import org.example.model.PriceCategory;
import org.example.model.Region;
import org.example.model.TransferMaterials;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-27T18:32:32+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
public class TransferMaterialsMapperImpl implements TransferMaterialsMapper {

    @Override
    public TransferMaterials entityToDomain(TransferMaterialsEntity transferMaterials) {
        if ( transferMaterials == null ) {
            return null;
        }

        TransferMaterials transferMaterials1 = new TransferMaterials();

        transferMaterials1.setPrice( transferMaterialsEntityToMoney( transferMaterials ) );
        transferMaterials1.setId( transferMaterials.getId() );
        transferMaterials1.setSource( gasStationEntityToGasStation( transferMaterials.getSource() ) );
        transferMaterials1.setDestination( gasStationEntityToGasStation( transferMaterials.getDestination() ) );
        transferMaterials1.setMaterial( materialEntityToMaterial( transferMaterials.getMaterial() ) );
        transferMaterials1.setAmount( transferMaterials.getAmount() );
        transferMaterials1.setCreatedAt( transferMaterials.getCreatedAt() );
        transferMaterials1.setUpdatedAt( transferMaterials.getUpdatedAt() );

        return transferMaterials1;
    }

    @Override
    public TransferMaterialsEntity domainToEntity(TransferMaterials transferMaterials) {
        if ( transferMaterials == null ) {
            return null;
        }

        TransferMaterialsEntity transferMaterialsEntity = new TransferMaterialsEntity();

        transferMaterialsEntity.setPriceCurrency( transferMaterialsPriceCurrency( transferMaterials ) );
        transferMaterialsEntity.setPriceAmount( transferMaterialsPriceAmount( transferMaterials ) );
        transferMaterialsEntity.setId( transferMaterials.getId() );
        transferMaterialsEntity.setSource( gasStationToGasStationEntity( transferMaterials.getSource() ) );
        transferMaterialsEntity.setDestination( gasStationToGasStationEntity( transferMaterials.getDestination() ) );
        transferMaterialsEntity.setMaterial( materialToMaterialEntity( transferMaterials.getMaterial() ) );
        transferMaterialsEntity.setAmount( transferMaterials.getAmount() );
        transferMaterialsEntity.setCreatedAt( transferMaterials.getCreatedAt() );
        transferMaterialsEntity.setUpdatedAt( transferMaterials.getUpdatedAt() );

        return transferMaterialsEntity;
    }

    protected Money transferMaterialsEntityToMoney(TransferMaterialsEntity transferMaterialsEntity) {
        if ( transferMaterialsEntity == null ) {
            return null;
        }

        Money money = new Money();

        money.setCurrency( transferMaterialsEntity.getPriceCurrency() );
        money.setAmount( transferMaterialsEntity.getPriceAmount() );

        return money;
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

    private String transferMaterialsPriceCurrency(TransferMaterials transferMaterials) {
        if ( transferMaterials == null ) {
            return null;
        }
        Money price = transferMaterials.getPrice();
        if ( price == null ) {
            return null;
        }
        String currency = price.getCurrency();
        if ( currency == null ) {
            return null;
        }
        return currency;
    }

    private Double transferMaterialsPriceAmount(TransferMaterials transferMaterials) {
        if ( transferMaterials == null ) {
            return null;
        }
        Money price = transferMaterials.getPrice();
        if ( price == null ) {
            return null;
        }
        Double amount = price.getAmount();
        if ( amount == null ) {
            return null;
        }
        return amount;
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
}
