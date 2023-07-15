package org.example.mappers;

import javax.annotation.processing.Generated;
import org.example.entities.GasStationEntity;
import org.example.entities.GroupEntity;
import org.example.entities.PersonEntity;
import org.example.entities.PriceCategoryEntity;
import org.example.entities.RegionEntity;
import org.example.model.GasStation;
import org.example.model.Group;
import org.example.model.Person;
import org.example.model.PriceCategory;
import org.example.model.Region;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-15T22:39:41+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
public class GasStationMapperImpl implements GasStationMapper {

    @Override
    public GasStationEntity domainToEntity(GasStation gasStation) {
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

    @Override
    public GasStation entityToDomain(GasStationEntity gasStation) {
        if ( gasStation == null ) {
            return null;
        }

        GasStation gasStation1 = new GasStation();

        gasStation1.setPlaceType( gasStation.getPlaceType() );
        gasStation1.setId( gasStation.getId() );
        gasStation1.setName( gasStation.getName() );
        gasStation1.setPriceCategory( priceCategoryEntityToPriceCategory( gasStation.getPriceCategory() ) );
        gasStation1.setDebtLimit( gasStation.getDebtLimit() );
        gasStation1.setRegion( regionEntityToRegion( gasStation.getRegion() ) );
        gasStation1.setOwner( personEntityToPerson( gasStation.getOwner() ) );
        gasStation1.setGroup( groupEntityToGroup( gasStation.getGroup() ) );
        gasStation1.setCreatedAt( gasStation.getCreatedAt() );

        return gasStation1;
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
}
