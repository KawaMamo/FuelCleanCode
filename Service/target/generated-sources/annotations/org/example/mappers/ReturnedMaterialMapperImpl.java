package org.example.mappers;

import javax.annotation.processing.Generated;
import org.example.entities.GasStationEntity;
import org.example.entities.GroupEntity;
import org.example.entities.MaterialEntity;
import org.example.entities.PersonEntity;
import org.example.entities.PriceCategoryEntity;
import org.example.entities.RegionEntity;
import org.example.entities.ReturnedMaterial;
import org.example.model.Buyer;
import org.example.model.GasStation;
import org.example.model.Group;
import org.example.model.Material;
import org.example.model.Money;
import org.example.model.Person;
import org.example.model.PriceCategory;
import org.example.model.Region;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-29T20:43:08+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
public class ReturnedMaterialMapperImpl implements ReturnedMaterialMapper {

    @Override
    public ReturnedMaterial domainToEntity(org.example.model.ReturnedMaterial returnedMaterial) {
        if ( returnedMaterial == null ) {
            return null;
        }

        ReturnedMaterial returnedMaterial1 = new ReturnedMaterial();

        returnedMaterial1.setPriceCurrency( returnedMaterialPriceCurrency( returnedMaterial ) );
        returnedMaterial1.setPriceAmount( returnedMaterialPriceAmount( returnedMaterial ) );
        returnedMaterial1.setCenterPriceCurrency( returnedMaterialCenterPriceCurrency( returnedMaterial ) );
        returnedMaterial1.setCenterPriceAmount( returnedMaterialCenterPriceAmount( returnedMaterial ) );
        returnedMaterial1.setId( returnedMaterial.getId() );
        returnedMaterial1.setGasStation( gasStationToGasStationEntity( returnedMaterial.getGasStation() ) );
        returnedMaterial1.setMaterial( materialToMaterialEntity( returnedMaterial.getMaterial() ) );
        returnedMaterial1.setAmount( returnedMaterial.getAmount() );
        returnedMaterial1.setStatus( returnedMaterial.getStatus() );
        returnedMaterial1.setBuyer( buyerToBuyer( returnedMaterial.getBuyer() ) );
        returnedMaterial1.setInvoiceNo( returnedMaterial.getInvoiceNo() );

        return returnedMaterial1;
    }

    @Override
    public org.example.model.ReturnedMaterial entityToDomain(ReturnedMaterial entity) {
        if ( entity == null ) {
            return null;
        }

        org.example.model.ReturnedMaterial returnedMaterial = new org.example.model.ReturnedMaterial();

        returnedMaterial.setPrice( returnedMaterialToMoney( entity ) );
        returnedMaterial.setCenterPrice( returnedMaterialToMoney1( entity ) );
        returnedMaterial.setId( entity.getId() );
        returnedMaterial.setGasStation( gasStationEntityToGasStation( entity.getGasStation() ) );
        returnedMaterial.setMaterial( materialEntityToMaterial( entity.getMaterial() ) );
        returnedMaterial.setAmount( entity.getAmount() );
        returnedMaterial.setStatus( entity.getStatus() );
        returnedMaterial.setBuyer( buyerToBuyer1( entity.getBuyer() ) );
        returnedMaterial.setInvoiceNo( entity.getInvoiceNo() );

        return returnedMaterial;
    }

    private String returnedMaterialPriceCurrency(org.example.model.ReturnedMaterial returnedMaterial) {
        if ( returnedMaterial == null ) {
            return null;
        }
        Money price = returnedMaterial.getPrice();
        if ( price == null ) {
            return null;
        }
        String currency = price.getCurrency();
        if ( currency == null ) {
            return null;
        }
        return currency;
    }

    private Double returnedMaterialPriceAmount(org.example.model.ReturnedMaterial returnedMaterial) {
        if ( returnedMaterial == null ) {
            return null;
        }
        Money price = returnedMaterial.getPrice();
        if ( price == null ) {
            return null;
        }
        Double amount = price.getAmount();
        if ( amount == null ) {
            return null;
        }
        return amount;
    }

    private String returnedMaterialCenterPriceCurrency(org.example.model.ReturnedMaterial returnedMaterial) {
        if ( returnedMaterial == null ) {
            return null;
        }
        Money centerPrice = returnedMaterial.getCenterPrice();
        if ( centerPrice == null ) {
            return null;
        }
        String currency = centerPrice.getCurrency();
        if ( currency == null ) {
            return null;
        }
        return currency;
    }

    private Double returnedMaterialCenterPriceAmount(org.example.model.ReturnedMaterial returnedMaterial) {
        if ( returnedMaterial == null ) {
            return null;
        }
        Money centerPrice = returnedMaterial.getCenterPrice();
        if ( centerPrice == null ) {
            return null;
        }
        Double amount = centerPrice.getAmount();
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

    protected org.example.entities.Buyer buyerToBuyer(Buyer buyer) {
        if ( buyer == null ) {
            return null;
        }

        org.example.entities.Buyer buyer1 = new org.example.entities.Buyer();

        buyer1.setId( buyer.getId() );
        buyer1.setName( buyer.getName() );
        buyer1.setOrganization( buyer.getOrganization() );

        return buyer1;
    }

    protected Money returnedMaterialToMoney(ReturnedMaterial returnedMaterial) {
        if ( returnedMaterial == null ) {
            return null;
        }

        Money money = new Money();

        money.setCurrency( returnedMaterial.getPriceCurrency() );
        money.setAmount( returnedMaterial.getPriceAmount() );

        return money;
    }

    protected Money returnedMaterialToMoney1(ReturnedMaterial returnedMaterial) {
        if ( returnedMaterial == null ) {
            return null;
        }

        Money money = new Money();

        money.setCurrency( returnedMaterial.getCenterPriceCurrency() );
        money.setAmount( returnedMaterial.getCenterPriceAmount() );

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
        region.setTranslation( regionEntity.getTranslation() );
        region.setPlaceType( regionEntity.getPlaceType() );
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

    protected GasStation gasStationEntityToGasStation(GasStationEntity gasStationEntity) {
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
        gasStation.setRegion( regionEntityToRegion( gasStationEntity.getRegion() ) );
        gasStation.setOwner( personEntityToPerson( gasStationEntity.getOwner() ) );
        gasStation.setGroup( groupEntityToGroup( gasStationEntity.getGroup() ) );
        gasStation.setMaterial( materialEntityToMaterial( gasStationEntity.getMaterial() ) );

        return gasStation;
    }

    protected Buyer buyerToBuyer1(org.example.entities.Buyer buyer) {
        if ( buyer == null ) {
            return null;
        }

        Buyer buyer1 = new Buyer();

        buyer1.setId( buyer.getId() );
        buyer1.setName( buyer.getName() );
        buyer1.setOrganization( buyer.getOrganization() );

        return buyer1;
    }
}
