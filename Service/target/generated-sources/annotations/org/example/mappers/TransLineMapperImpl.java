package org.example.mappers;

import javax.annotation.processing.Generated;
import org.example.entities.GasStationEntity;
import org.example.entities.GroupEntity;
import org.example.entities.MaterialEntity;
import org.example.entities.PersonEntity;
import org.example.entities.PriceCategoryEntity;
import org.example.entities.RefineryEntity;
import org.example.entities.RegionEntity;
import org.example.entities.TransLineEntity;
import org.example.model.GasStation;
import org.example.model.Group;
import org.example.model.Material;
import org.example.model.Money;
import org.example.model.Person;
import org.example.model.PriceCategory;
import org.example.model.Refinery;
import org.example.model.Region;
import org.example.model.TransLine;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-30T19:14:31+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
public class TransLineMapperImpl implements TransLineMapper {

    @Override
    public TransLineEntity domainToEntity(TransLine transLine) {
        if ( transLine == null ) {
            return null;
        }

        TransLineEntity transLineEntity = new TransLineEntity();

        transLineEntity.setSource( toEntity( transLine.getSource() ) );
        transLineEntity.setDestination( toEntity( transLine.getDestination() ) );
        transLineEntity.setFeeCurrency( transLineFeeCurrency( transLine ) );
        transLineEntity.setFeeAmount( transLineFeeAmount( transLine ) );
        transLineEntity.setId( transLine.getId() );
        transLineEntity.setCreatedAt( transLine.getCreatedAt() );
        transLineEntity.setUpdatedAt( transLine.getUpdatedAt() );

        return transLineEntity;
    }

    @Override
    public TransLine entityToDomain(TransLineEntity transLine) {
        if ( transLine == null ) {
            return null;
        }

        TransLine transLine1 = new TransLine();

        transLine1.setFee( transLineEntityToMoney( transLine ) );
        transLine1.setSource( toDomain( transLine.getSource() ) );
        transLine1.setDestination( toDomain( transLine.getDestination() ) );
        transLine1.setId( transLine.getId() );
        transLine1.setCreatedAt( transLine.getCreatedAt() );
        transLine1.setUpdatedAt( transLine.getUpdatedAt() );

        return transLine1;
    }

    @Override
    public RegionEntity RegionToEntity(Region source) {
        if ( source == null ) {
            return null;
        }

        RegionEntity regionEntity = new RegionEntity();

        regionEntity.setId( source.getId() );
        regionEntity.setPlaceType( source.getPlaceType() );
        regionEntity.setCreatedAt( source.getCreatedAt() );
        regionEntity.setUpdatedAt( source.getUpdatedAt() );
        regionEntity.setName( source.getName() );
        regionEntity.setTranslation( source.getTranslation() );

        return regionEntity;
    }

    @Override
    public GasStationEntity stationToEntity(GasStation gasStation) {
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
        gasStationEntity.setRegion( RegionToEntity( gasStation.getRegion() ) );
        gasStationEntity.setOwner( personToPersonEntity( gasStation.getOwner() ) );
        gasStationEntity.setGroup( groupToGroupEntity( gasStation.getGroup() ) );
        gasStationEntity.setMaterial( materialToMaterialEntity( gasStation.getMaterial() ) );

        return gasStationEntity;
    }

    @Override
    public RefineryEntity refineryToEntity(Refinery refinery) {
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

    @Override
    public Region RegionToDomain(RegionEntity source) {
        if ( source == null ) {
            return null;
        }

        Region region = new Region();

        region.setId( source.getId() );
        region.setName( source.getName() );
        region.setTranslation( source.getTranslation() );
        region.setPlaceType( source.getPlaceType() );
        region.setCreatedAt( source.getCreatedAt() );
        region.setUpdatedAt( source.getUpdatedAt() );

        return region;
    }

    @Override
    public GasStation stationToDomain(GasStationEntity gasStation) {
        if ( gasStation == null ) {
            return null;
        }

        GasStation gasStation1 = new GasStation();

        gasStation1.setId( gasStation.getId() );
        gasStation1.setName( gasStation.getName() );
        gasStation1.setTranslation( gasStation.getTranslation() );
        gasStation1.setPlaceType( gasStation.getPlaceType() );
        gasStation1.setCreatedAt( gasStation.getCreatedAt() );
        gasStation1.setUpdatedAt( gasStation.getUpdatedAt() );
        gasStation1.setPriceCategory( priceCategoryEntityToPriceCategory( gasStation.getPriceCategory() ) );
        gasStation1.setRegion( RegionToDomain( gasStation.getRegion() ) );
        gasStation1.setOwner( personEntityToPerson( gasStation.getOwner() ) );
        gasStation1.setGroup( groupEntityToGroup( gasStation.getGroup() ) );
        gasStation1.setMaterial( materialEntityToMaterial( gasStation.getMaterial() ) );

        return gasStation1;
    }

    @Override
    public Refinery refineryToDomain(RefineryEntity refinery) {
        if ( refinery == null ) {
            return null;
        }

        Refinery refinery1 = new Refinery();

        refinery1.setId( refinery.getId() );
        refinery1.setName( refinery.getName() );
        refinery1.setTranslation( refinery.getTranslation() );
        refinery1.setPlaceType( refinery.getPlaceType() );
        refinery1.setCreatedAt( refinery.getCreatedAt() );
        refinery1.setUpdatedAt( refinery.getUpdatedAt() );

        return refinery1;
    }

    private String transLineFeeCurrency(TransLine transLine) {
        if ( transLine == null ) {
            return null;
        }
        Money fee = transLine.getFee();
        if ( fee == null ) {
            return null;
        }
        String currency = fee.getCurrency();
        if ( currency == null ) {
            return null;
        }
        return currency;
    }

    private Double transLineFeeAmount(TransLine transLine) {
        if ( transLine == null ) {
            return null;
        }
        Money fee = transLine.getFee();
        if ( fee == null ) {
            return null;
        }
        Double amount = fee.getAmount();
        if ( amount == null ) {
            return null;
        }
        return amount;
    }

    protected Money transLineEntityToMoney(TransLineEntity transLineEntity) {
        if ( transLineEntity == null ) {
            return null;
        }

        Money money = new Money();

        money.setCurrency( transLineEntity.getFeeCurrency() );
        money.setAmount( transLineEntity.getFeeAmount() );

        return money;
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
}
