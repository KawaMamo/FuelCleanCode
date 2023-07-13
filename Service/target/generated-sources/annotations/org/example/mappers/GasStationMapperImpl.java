package org.example.mappers;

import javax.annotation.processing.Generated;
import org.example.entities.GasStationEntity;
import org.example.model.GasStation;
import org.example.model.Group;
import org.example.model.Person;
import org.example.model.PriceCategory;
import org.example.model.Region;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-13T01:12:22+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
public class GasStationMapperImpl implements GasStationMapper {

    @Override
    public GasStationEntity domainToEntity(GasStation gasStation) {
        if ( gasStation == null ) {
            return null;
        }

        GasStationEntity gasStationEntity = new GasStationEntity();

        gasStationEntity.setPriceCategoryId( gasStationPriceCategoryId( gasStation ) );
        gasStationEntity.setRegionId( gasStationRegionId( gasStation ) );
        gasStationEntity.setOwnerId( gasStationOwnerId( gasStation ) );
        gasStationEntity.setGroupId( gasStationGroupId( gasStation ) );
        gasStationEntity.setId( gasStation.getId() );
        gasStationEntity.setName( gasStation.getName() );
        gasStationEntity.setCreatedAt( gasStation.getCreatedAt() );
        gasStationEntity.setDebtLimit( gasStation.getDebtLimit() );

        return gasStationEntity;
    }

    @Override
    public GasStation entityToDomain(GasStationEntity gasStation) {
        if ( gasStation == null ) {
            return null;
        }

        GasStation gasStation1 = new GasStation();

        gasStation1.setPriceCategory( gasStationEntityToPriceCategory( gasStation ) );
        gasStation1.setRegion( gasStationEntityToRegion( gasStation ) );
        gasStation1.setOwner( gasStationEntityToPerson( gasStation ) );
        gasStation1.setGroup( gasStationEntityToGroup( gasStation ) );
        gasStation1.setId( gasStation.getId() );
        gasStation1.setName( gasStation.getName() );
        gasStation1.setDebtLimit( gasStation.getDebtLimit() );
        gasStation1.setCreatedAt( gasStation.getCreatedAt() );

        return gasStation1;
    }

    private Long gasStationPriceCategoryId(GasStation gasStation) {
        if ( gasStation == null ) {
            return null;
        }
        PriceCategory priceCategory = gasStation.getPriceCategory();
        if ( priceCategory == null ) {
            return null;
        }
        Long id = priceCategory.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long gasStationRegionId(GasStation gasStation) {
        if ( gasStation == null ) {
            return null;
        }
        Region region = gasStation.getRegion();
        if ( region == null ) {
            return null;
        }
        Long id = region.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long gasStationOwnerId(GasStation gasStation) {
        if ( gasStation == null ) {
            return null;
        }
        Person owner = gasStation.getOwner();
        if ( owner == null ) {
            return null;
        }
        Long id = owner.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long gasStationGroupId(GasStation gasStation) {
        if ( gasStation == null ) {
            return null;
        }
        Group group = gasStation.getGroup();
        if ( group == null ) {
            return null;
        }
        Long id = group.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected PriceCategory gasStationEntityToPriceCategory(GasStationEntity gasStationEntity) {
        if ( gasStationEntity == null ) {
            return null;
        }

        PriceCategory priceCategory = new PriceCategory();

        priceCategory.setId( gasStationEntity.getPriceCategoryId() );

        return priceCategory;
    }

    protected Region gasStationEntityToRegion(GasStationEntity gasStationEntity) {
        if ( gasStationEntity == null ) {
            return null;
        }

        Region region = new Region();

        region.setId( gasStationEntity.getRegionId() );

        return region;
    }

    protected Person gasStationEntityToPerson(GasStationEntity gasStationEntity) {
        if ( gasStationEntity == null ) {
            return null;
        }

        Person person = new Person();

        person.setId( gasStationEntity.getOwnerId() );

        return person;
    }

    protected Group gasStationEntityToGroup(GasStationEntity gasStationEntity) {
        if ( gasStationEntity == null ) {
            return null;
        }

        Group group = new Group();

        group.setId( gasStationEntity.getGroupId() );

        return group;
    }
}
